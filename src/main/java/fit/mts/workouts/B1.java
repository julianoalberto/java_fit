package fit.mts.workouts;

import static fit.mts.Zone.N1_BLACK;
import static fit.mts.Zone.N1_GREEN;
import static fit.mts.Zone.N2_BLACK;
import static fit.mts.Zone.N3_RED;
import static fit.mts.Zone.N4_BLACK;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;
import fit.mts.WorkoutStepZoneSeconds;

public class B1 extends MTSGarminWorkout {
    public static final String NAME = "B1";

    public B1(Long workoutSerial) {
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

        // 20mn N2-75rpm
        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 20, "75 RPM"));

        // 5mn N1/85rpm bem soft
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM NX UP"));

        w.addStep(new WorkoutStepZoneMinutes(N3_RED, 3, "55 RPM UP"));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 3, "85 RPM"));
        fromStep = w.getTotalSteps() - 1;
        w.addStep(new WorkoutStepRepeat(fromStep, 5));

        // 5mn N1-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 5, "85 RPM"));

        // 20mn N2-85rpm em plano
        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 15, "85 RPM"));

        // 10mn N1-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 10, "85 RPM"));
    }
}
