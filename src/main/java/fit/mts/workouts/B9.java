package fit.mts.workouts;

import static fit.mts.Zone.N1_BLACK;
import static fit.mts.Zone.N1_GREEN;
import static fit.mts.Zone.N2_BLACK;
import static fit.mts.Zone.N3_RED;
import static fit.mts.Zone.N4_BLACK;
import static fit.mts.Zone.N5_BLACK;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;
import fit.mts.WorkoutStepZoneSeconds;

public class B9 extends MTSGarminWorkout {
    public static final String NAME = "B9";

    public B9(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        // 10mn N1-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 10, "85 RPM"));

        // 20mn N2-95rpm c/ 6 sprints força
        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 3, "95 RPM NX STOP"));
        w.addStep(new WorkoutStepZoneSeconds(N2_BLACK, 15, "STOP MAX"));
        int fromStep = w.getTotalSteps() - 1;
        w.addStep(new WorkoutStepRepeat(fromStep, 6));

        // 20mn N3-90rpm
        w.addStep(new WorkoutStepZoneMinutes(N3_RED, 20, "90 RPM"));

        // 5mn N1/85rpm bem soft
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM NX UP"));

        w.addStep(new WorkoutStepZoneMinutes(N4_BLACK, 4, "50 RPM UP"));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 3, "85 RPM"));
        fromStep = w.getTotalSteps() - 1;
        w.addStep(new WorkoutStepRepeat(fromStep, 6));

        // 5mn N1-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 5, "85 RPM"));

        // 15mn N3-65/70rpm em plano
        w.addStep(new WorkoutStepZoneMinutes(N3_RED, 15, "70 RPM"));

        // 10mn N1-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 10, "85 RPM"));
    }
}
