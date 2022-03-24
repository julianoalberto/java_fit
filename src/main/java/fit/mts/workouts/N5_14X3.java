package fit.mts.workouts;

import static fit.mts.Zone.N1_BLACK;
import static fit.mts.Zone.N1_GREEN;
import static fit.mts.Zone.N2_BLACK;
import static fit.mts.Zone.N5_BLACK;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;
import fit.mts.WorkoutStepZoneSeconds;

public class N5_14X3 extends MTSGarminWorkout {
    public static final String NAME = "N5-14x3";

    public N5_14X3(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 45, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "N5"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 15, "N1"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 14));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "N5"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 15, "N1"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 14));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "N5"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 15, "N1"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 14));

        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 45, "90 RPM"));
    }
}
