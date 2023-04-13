package fit.mts.workouts;

import static fit.mts.Zone.N1_BLACK;
import static fit.mts.Zone.N1_GREEN;
import static fit.mts.Zone.N3_GREEN;
import static fit.mts.Zone.N5_BLACK;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;

public class N5_2MX4 extends MTSGarminWorkout {
    public static final String NAME = "N5-2MX4";

    public N5_2MX4(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        // warm-up
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 10, "85 RPM"));

        // block 1
        w.addStep(new WorkoutStepZoneMinutes(N5_BLACK, 2, "85 RPM MAX"));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 2, "85 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));

        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM"));

        // block 2
        w.addStep(new WorkoutStepZoneMinutes(N5_BLACK, 2, "85 RPM MAX"));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 2, "85 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));

        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 8, "85 RPM"));

        w.addStep(new WorkoutStepZoneMinutes(N3_GREEN, 20, "90 RPM"));

        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 60, "85 RPM"));
    }
}
