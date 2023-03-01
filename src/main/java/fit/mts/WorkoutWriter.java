package fit.mts;

import java.io.File;

import com.garmin.fit.FileEncoder;
import com.garmin.fit.Fit;
import com.garmin.fit.FitRuntimeException;
import com.garmin.fit.Mesg;

import fit.model.GarminWorkout;
import fit.mts.workouts.MTSGarminWorkout;

public class WorkoutWriter {
    public static final String WORKOUT_FILE_SUFFIX = "_workout.fit";
    public static final String WORKOUT_OUTPUT_DIR = "Workouts";
    
    public static void writeMTSGarminWorkout(MTSGarminWorkout mtsGarminWorkout) {
        System.out.println("Encode Workout");
        GarminWorkout w = mtsGarminWorkout.getWorkout();
        FileEncoder encode;
        String filePath = WORKOUT_OUTPUT_DIR + File.separator + w.getWorkoutData().getWorkoutName() 
                            + WORKOUT_FILE_SUFFIX;

        try {
            encode = new FileEncoder(new java.io.File(filePath), Fit.ProtocolVersion.V1_0);
        } catch (FitRuntimeException e) {
            System.err.printf("Error opening file %s.\n", filePath);
            return;
        }

        for (Mesg msg : w.asMessages()) {
            encode.write(msg);
        }

        try {
            encode.close();
        } catch (FitRuntimeException e) {
            System.err.println("Error closing encode.");
            return;
        }

        System.out.printf("Encoded FIT file %s.\n", filePath);
    }
}
