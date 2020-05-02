package fit.mvp;

import com.garmin.fit.*;
import java.util.Date;

public class EncodeWorkout {
    public static void main(String[] args) {
        System.out.printf("FIT Encode Example Application - Protocol %d.%d Profile %.2f %s\n",
                           Fit.PROTOCOL_VERSION_MAJOR,
                           Fit.PROTOCOL_VERSION_MINOR,
                           Fit.PROFILE_VERSION / 100.0,
                           Fit.PROFILE_TYPE);

        new EncodeWorkout().encodeWorkout();
    }

    private void encodeWorkout() {
        System.out.println("Encode Workout");

        FileEncoder encode;
        String filePath = "AAA_workout.fit";

        try {
            encode = new FileEncoder(new java.io.File(filePath), Fit.ProtocolVersion.V1_0);
        } catch (FitRuntimeException e) {
            System.err.printf("Error opening file %s.\n", filePath);
            return;
        }

        writeFileIdMesg(encode);
        writeWorkoutMesg(encode);
        warmUp(encode);
        sprint(encode);
        recover(encode);
        repeat(encode);
        coolDown(encode);       

        try {
            encode.close();
        } catch (FitRuntimeException e) {
            System.err.println("Error closing encode.");
            return;
        }

        System.out.printf("Encoded FIT file %s.\n", filePath);
    }

    private void writeFileIdMesg(FileEncoder encode) {
        FileIdMesg msg = new FileIdMesg(); // Every FIT file MUST contain a 'File ID' message as the first message
        Date now = new Date();
        msg.setType(File.WORKOUT);
        msg.setManufacturer(Manufacturer.GARMIN);
        msg.setProduct(GarminProduct.EDGE500);
        msg.setSerialNumber(generateSerialNumber(now));
        msg.setTimeCreated(new DateTime(now));

        encode.write(msg);
    }

    private void writeWorkoutMesg(FileEncoder encode) {
        WorkoutMesg msg = new WorkoutMesg();
        msg.setSport(Sport.CYCLING);
        msg.setNumValidSteps(5);
        msg.setWktName("AAA");
        encode.write(msg);
    }

    private void warmUp(FileEncoder encode) {
        WorkoutStepMesg msg = new WorkoutStepMesg();
        msg.setMessageIndex(0);
        msg.setWktStepName("85 RPM");
        msg.setIntensity(Intensity.WARMUP);

        msg.setDurationType(WktStepDuration.TIME);
        msg.setDurationValue(20l * 1000);

        msg.setTargetType(WktStepTarget.HEART_RATE);
        msg.setTargetValue(0L); // custom
        msg.setCustomTargetHeartRateLow(247L);
        msg.setCustomTargetHeartRateHigh(253L);

        encode.write(msg);
    }

    private void sprint(FileEncoder encode) {
        WorkoutStepMesg msg = new WorkoutStepMesg();
        msg.setMessageIndex(1);
        msg.setWktStepName("N5");
        msg.setIntensity(Intensity.ACTIVE);
        
        msg.setDurationType(WktStepDuration.TIME);
        msg.setDurationValue(15l * 1000);

        msg.setTargetType(WktStepTarget.HEART_RATE);
        msg.setTargetValue(0L); // custom
        msg.setCustomTargetHeartRateLow(277L);
        msg.setCustomTargetHeartRateHigh(283L);

        encode.write(msg);
    }

    private void recover(FileEncoder encode) {
        WorkoutStepMesg msg = new WorkoutStepMesg();
        msg.setMessageIndex(2);
        msg.setWktStepName("N1");
        msg.setIntensity(Intensity.REST);
        
        msg.setDurationType(WktStepDuration.TIME);
        msg.setDurationValue(15l * 1000);

        msg.setTargetType(WktStepTarget.HEART_RATE);
        msg.setTargetValue(0L); // custom
        msg.setCustomTargetHeartRateLow(232L);
        msg.setCustomTargetHeartRateHigh(238L);

        encode.write(msg);
    }

    private void repeat(FileEncoder encode) {
        WorkoutStepMesg msg = new WorkoutStepMesg();
        msg.setMessageIndex(3);
        
        msg.setDurationType(WktStepDuration.REPEAT_UNTIL_STEPS_CMPLT);
        msg.setDurationValue(1L); // from stap

        msg.setTargetValue(3L); // number of repetitions

        encode.write(msg);
    }

    private void coolDown(FileEncoder encode) {
        WorkoutStepMesg msg = new WorkoutStepMesg();
        msg.setMessageIndex(4);
        msg.setWktStepName("85 RPM");
        msg.setIntensity(Intensity.COOLDOWN);
        
        msg.setDurationType(WktStepDuration.TIME);
        msg.setDurationValue(300l * 1000);

        msg.setTargetType(WktStepTarget.HEART_RATE);
        msg.setTargetValue(0L); // custom
        msg.setCustomTargetHeartRateLow(237L);
        msg.setCustomTargetHeartRateHigh(243L);

        encode.write(msg);
    }

    private long generateSerialNumber(Date date) {
        return date.getTime() / 1000;
    }
}

