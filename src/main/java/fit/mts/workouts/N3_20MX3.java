package fit.mts.workouts;

import static fit.mts.Zone.N1_BLACK;
import static fit.mts.Zone.N2_BLACK;
import static fit.mts.Zone.N3_RED;
import static fit.mts.Zone.N5_BLACK;


import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;

public class N3_20MX3 extends MTSGarminWorkout {
    public static final String NAME = "N3-20MX3";

    public N3_20MX3(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 15, "85 RPM"));

        w.addStep(new WorkoutStepZoneMinutes(N3_RED, 20, "75 RPM"));
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 5, "85 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 3));

        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 90, "85 RPM"));
    }
}
