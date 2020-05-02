package fit.mvp;

import com.garmin.fit.*;

import java.io.FileInputStream;
import java.io.InputStream;

public class DecodeWorkout {
    public static void main(String[] args) {
        Decode decode = new Decode();
        //decode.skipHeader();        // Use on streams with no header and footer (stream contains FIT defn and data messages only)
        //decode.incompleteStream();  // This suppresses exceptions with unexpected eof (also incorrect crc)
        MesgBroadcaster mesgBroadcaster = new MesgBroadcaster(decode);
        Listener listener = new Listener();
        FileInputStream in;

        String filePath = "AAA_workout.fit";
        
        System.out.println("Decoding FIT file: " + filePath);

        try {
            in = new FileInputStream(filePath);
        } catch (java.io.IOException e) {
            throw new RuntimeException("Error opening file " + filePath);
        }

        try {
            if (!decode.checkFileIntegrity((InputStream)in)) {
                throw new RuntimeException("FIT file integrity failed.");
            }
        } catch (RuntimeException e) {
            System.err.print("Exception Checking File Integrity: ");
            System.err.println(e.getMessage());
            System.err.println("Trying to continue...");
        } finally {
            try {
                in.close();
            } catch (java.io.IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            in = new FileInputStream(filePath);
        } catch (java.io.IOException e) {
            throw new RuntimeException("Error opening file " + filePath);
        }

        mesgBroadcaster.addListener((FileIdMesgListener)listener);
        mesgBroadcaster.addListener((WorkoutMesgListener)listener);
        mesgBroadcaster.addListener((WorkoutStepMesgListener)listener);
        

        try {
            decode.read(in, mesgBroadcaster, mesgBroadcaster);
        } catch (FitRuntimeException e) {
            // If a file with 0 data size in it's header  has been encountered,
            // attempt to keep processing the file
            if (decode.getInvalidFileDataSize()) {
                decode.nextFile();
                decode.read(in, mesgBroadcaster, mesgBroadcaster);
            } else {
                System.err.print("Exception decoding file: ");
                System.err.println(e.getMessage());

                try {
                    in.close();
                } catch (java.io.IOException f) {
                    throw new RuntimeException(f);
                }

                return;
            }
        }

        try {
            in.close();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Decoded FIT file: " + filePath);
    }

    private static class Listener implements WorkoutStepMesgListener, WorkoutMesgListener, FileIdMesgListener {
        @Override
        public void onMesg(FileIdMesg msg) {
            System.out.println(msg);
            System.out.println(msg.getType());
            System.out.println(msg.getManufacturer());
            System.out.println(msg.getProduct());
            System.out.println(msg.getSerialNumber());
            System.out.println(msg.getTimeCreated());
        }        

        @Override
        public void onMesg(WorkoutMesg mesg) {
            System.out.println(mesg);
            System.out.println(mesg.getSport());
            System.out.println(mesg.getNumValidSteps());
            System.out.println(mesg.getWktName());            
        }

        @Override
        public void onMesg(WorkoutStepMesg msg) {
            System.out.println(msg);
            System.out.println(msg.getMessageIndex());
            System.out.println(msg.getWktStepName());
            System.out.println(msg.getIntensity());
            
            System.out.println(msg.getDurationType());
            System.out.println(msg.getDurationTime());

            System.out.println(msg.getTargetType());
            System.out.println(msg.getTargetValue());
            System.out.println(msg.getCustomTargetHeartRateLow());
            System.out.println(msg.getCustomTargetHeartRateHigh());

        }
    }    
}
