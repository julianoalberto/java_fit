package fit.mts.workouts;

import static fit.mts.Zone.N1_BLACK;
import static fit.mts.Zone.N1_GREEN;
import static fit.mts.Zone.N3_GREEN;
import static fit.mts.Zone.N5_BLACK;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;

public class N5_4MX3 extends MTSGarminWorkout {
    public static final String NAME = "N5-4MX3";

    public N5_4MX3(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        // warm-up
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 10, "85 RPM"));

        // block 1
        w.addStep(new WorkoutStepZoneMinutes(N5_BLACK, 4, "85 RPM MAX"));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 4, "85 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 3));
        
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 8, "85 RPM"));
        
        // block 2
        w.addStep(new WorkoutStepZoneMinutes(N5_BLACK, 4, "85 RPM MAX"));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 4, "85 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 3));

        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 8, "85 RPM"));

        w.addStep(new WorkoutStepZoneMinutes(N3_GREEN, 20, "90 RPM"));

        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 60, "85 RPM"));
    }
}
