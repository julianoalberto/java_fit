package fit.mts.workouts;

import static fit.mts.Zone.*;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;

public class N2_N3_10M extends MTSGarminWorkout {
    public static final String NAME = "N2-N3-10M";

    public N2_N3_10M(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 10, "85 RPM"));

        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 10, "85 RPM"));
        w.addStep(new WorkoutStepZoneMinutes(N3_RED, 10, "70 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));
    }
}
