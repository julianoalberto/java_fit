package fit.mvp;

import java.lang.reflect.Array;
import java.util.Date;

import com.garmin.fit.DateTime;
import com.garmin.fit.File;
import com.garmin.fit.FileEncoder;
import com.garmin.fit.FileIdMesg;
import com.garmin.fit.Fit;
import com.garmin.fit.FitRuntimeException;
import com.garmin.fit.GarminProduct;
import com.garmin.fit.Intensity;
import com.garmin.fit.Manufacturer;
import com.garmin.fit.Sport;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutMesg;
import com.garmin.fit.WorkoutStepMesg;

import fit.model.GarminWorkout;
import fit.model.WorkoutFactory;
import fit.model.WorkoutStep;
import fit.model.WorkoutStepHeartRate;
import fit.model.WorkoutStepRepeat;

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

        GarminWorkout w = (GarminWorkout) WorkoutFactory.createWorkout(WorkoutFactory.Type.GARMIN_500_CYCLING);

        w.addStep(new WorkoutStepHeartRate(147, 153, 20, "85 RPM"));
        w.addStep(new WorkoutStepHeartRate(177, 183, 15, "N5"));
        w.addStep(new WorkoutStepHeartRate(132, 138, 15, "N1"));
        w.addStep(new WorkoutStepRepeat(1, 3));
        w.addStep(new WorkoutStepHeartRate(137, 143, 300, "85 RPM"));
         //TEST adding steps and getting from Array
         
        writeFileIdMesg(w, encode);
        writeWorkoutMesg(w, encode);

        for (WorkoutStep s : w.getSteps()) {
            encode.write(s.getWorkoutStepMesg());
        }

        // warmUp(encode);
        // sprint(encode);
        // recover(encode);
        // repeat(encode);
        // coolDown(encode);       

        try {
            encode.close();
        } catch (FitRuntimeException e) {
            System.err.println("Error closing encode.");
            return;
        }

        System.out.printf("Encoded FIT file %s.\n", filePath);
    }

    private void writeFileIdMesg(GarminWorkout w, FileEncoder encode) {
        FileIdMesg msg = w.getWorkoutMetadata().getFileIdMesg();
        Date now = new Date();
        msg.setSerialNumber(generateSerialNumber(now));
        msg.setTimeCreated(new DateTime(now));

        encode.write(msg);
    }

    private void writeWorkoutMesg(GarminWorkout w, FileEncoder encode) {
        WorkoutMesg msg = w.getWorkoutData().getWorkoutMesg();
        msg.setWktName("AAA");
        encode.write(msg);
    }

    private void warmUp(FileEncoder encode) {
        WorkoutStepHeartRate s = new WorkoutStepHeartRate(147, 153, 20, "85 RPM");
        WorkoutStepMesg msg = s.getWorkoutStepMesg();
        msg.setMessageIndex(0);
        // msg.setWktStepName("85 RPM");
        // msg.setIntensity(Intensity.WARMUP);

        // msg.setDurationType(WktStepDuration.TIME);
        // msg.setDurationValue(20l * 1000);

        // msg.setTargetType(WktStepTarget.HEART_RATE);
        // msg.setTargetValue(0L); // custom
        // msg.setCustomTargetHeartRateLow(247L);
        // msg.setCustomTargetHeartRateHigh(253L);

        encode.write(msg);
    }

    private void sprint(FileEncoder encode) {
        WorkoutStepHeartRate s = new WorkoutStepHeartRate(177, 183, 15, "N5");
        WorkoutStepMesg msg = s.getWorkoutStepMesg();
        msg.setMessageIndex(1);
        // msg.setWktStepName("N5");
        // msg.setIntensity(Intensity.ACTIVE);
        
        // msg.setDurationType(WktStepDuration.TIME);
        // msg.setDurationValue(15l * 1000);

        // msg.setTargetType(WktStepTarget.HEART_RATE);
        // msg.setTargetValue(0L); // custom
        // msg.setCustomTargetHeartRateLow(277L);
        // msg.setCustomTargetHeartRateHigh(283L);

        encode.write(msg);
    }

    private void recover(FileEncoder encode) {
        WorkoutStepHeartRate s = new WorkoutStepHeartRate(132, 138, 15, "N1");
        WorkoutStepMesg msg = s.getWorkoutStepMesg();
        msg.setMessageIndex(2);
        // msg.setWktStepName("N1");
        // msg.setIntensity(Intensity.REST);
        
        // msg.setDurationType(WktStepDuration.TIME);
        // msg.setDurationValue(15l * 1000);

        // msg.setTargetType(WktStepTarget.HEART_RATE);
        // msg.setTargetValue(0L); // custom
        // msg.setCustomTargetHeartRateLow(232L);
        // msg.setCustomTargetHeartRateHigh(238L);

        encode.write(msg);
    }

    private void repeat(FileEncoder encode) {
        WorkoutStepRepeat s = new WorkoutStepRepeat(1, 3);
        WorkoutStepMesg msg = s.getWorkoutStepMesg();
        // msg.setMessageIndex(3);
        
        // msg.setDurationType(WktStepDuration.REPEAT_UNTIL_STEPS_CMPLT);
        // msg.setDurationValue(1L); // from step

        // msg.setTargetValue(3L); // number of repetitions

        encode.write(msg);
    }

    private void coolDown(FileEncoder encode) {
        WorkoutStepHeartRate s = new WorkoutStepHeartRate(137, 143, 300, "85 RPM");
        WorkoutStepMesg msg = s.getWorkoutStepMesg();
        msg.setMessageIndex(4);
        // msg.setWktStepName("85 RPM");
        // msg.setIntensity(Intensity.COOLDOWN);
        
        // msg.setDurationType(WktStepDuration.TIME);
        // msg.setDurationValue(300l * 1000);

        // msg.setTargetType(WktStepTarget.HEART_RATE);
        // msg.setTargetValue(0L); // custom
        // msg.setCustomTargetHeartRateLow(237L);
        // msg.setCustomTargetHeartRateHigh(243L);

        encode.write(msg);
    }

    private long generateSerialNumber(Date date) {
        return date.getTime() / 1000;
    }
}

