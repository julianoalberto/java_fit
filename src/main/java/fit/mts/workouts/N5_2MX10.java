package fit.mts.workouts;

import static fit.mts.Zone.N1_BLACK;
import static fit.mts.Zone.N2_BLACK;
import static fit.mts.Zone.N5_BLACK;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;
import fit.mts.WorkoutStepZoneSeconds;

public class N5_2MX10 extends MTSGarminWorkout {
    public static final String NAME = "N5-2MX10";

    public N5_2MX10(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 30, "90 RPM"));

        w.addStep(new WorkoutStepZoneMinutes(N5_BLACK, 2, "N5"));
        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 2, "N2"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 10));

        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 60, "85 RPM"));
    }
}
