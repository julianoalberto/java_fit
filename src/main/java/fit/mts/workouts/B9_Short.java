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

public class B9_Short extends MTSGarminWorkout {
    public static final String NAME = "B9-SHORT";

    public B9_Short(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        // 5mn N1-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 5, "85 RPM"));
        
        // 20mn N3-95rpm c/ 6 sprints força
        w.addStep(new WorkoutStepZoneMinutes(N3_RED, 3, "95 RPM NX STOP"));
        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 15, "STOP MAX"));
        int fromStep = w.getTotalSteps() - 1;
        w.addStep(new WorkoutStepRepeat(fromStep, 6));

        // 5mn N1/85rpm bem soft
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM NX UP"));

        // 6xs 5mn N4-55rpm a subir / 3mn N1-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N4_BLACK, 5, "55 RPM UP"));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 3, "85 RPM"));
        fromStep = w.getTotalSteps() - 1;
        w.addStep(new WorkoutStepRepeat(fromStep, 6));

        // 5mn N3-85/90rpm em plano
        w.addStep(new WorkoutStepZoneMinutes(N3_RED, 5, "90 RPM"));

        // 5mn N1-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM"));
    }
}
