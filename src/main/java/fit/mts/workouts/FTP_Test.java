package fit.mts.workouts;

import static fit.mts.Zone.*;

import fit.mts.WorkoutStepZoneMinutes;

public class FTP_Test extends MTSGarminWorkout {
    public static final String NAME = "FTP-TEST";

    public FTP_Test(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        w.addStep(new WorkoutStepZoneMinutes(N5_BLACK, 10, "STRONG"));
        w.addStep(new WorkoutStepZoneMinutes(N5_BLACK, 7, "MAX"));
        w.addStep(new WorkoutStepZoneMinutes(N5_BLACK, 3, "ALL"));
    }
}
