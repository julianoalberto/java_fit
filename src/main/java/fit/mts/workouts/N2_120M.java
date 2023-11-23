package fit.mts.workouts;

import static fit.mts.Zone.N2_BLACK;

import fit.mts.WorkoutStepZoneMinutes;

public class N2_120M extends MTSGarminWorkout {
    public static final String NAME = "N2-120M";

    public N2_120M(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        // 120mn N2-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 120, "85 RPM"));
    }
}
