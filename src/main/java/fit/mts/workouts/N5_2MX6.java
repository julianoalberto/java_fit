package fit.mts.workouts;

import static fit.mts.Zone.N1_BLACK;
import static fit.mts.Zone.N5_BLACK;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;
import fit.mts.WorkoutStepZoneSeconds;

public class N5_2MX6 extends MTSGarminWorkout {
    public static final String NAME = "N5-2MX6";

    public N5_2MX6(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 20, "95 RPM NX MAX"));
        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "MAX"));

        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 270, "95 RPM NX MAX"));
        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "MAX"));

        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 270, "95 RPM"));

        w.addStep(new WorkoutStepZoneMinutes(N5_BLACK, 2, "N5"));
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 5, "N1"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 6));

        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 48, "95 RPM"));
    }
}
