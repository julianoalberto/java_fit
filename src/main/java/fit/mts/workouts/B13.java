package fit.mts.workouts;

import static fit.mts.Zone.*;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;
import fit.mts.WorkoutStepZoneSeconds;

public class B13 extends MTSGarminWorkout {
    public static final String NAME = "B13";

    public B13(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        // 10mn N1-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 10, "85 RPM"));

        // 20mn N2-95rpm c/ 6 sprints força
        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 3, "95 RPM NX STOP"));
        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 15, "STOP MAX"));
        int fromStep = w.getTotalSteps() - 1;
        w.addStep(new WorkoutStepRepeat(fromStep, 6));

        // 20mn N4-90rpm
        w.addStep(new WorkoutStepZoneMinutes(N4_GREEN, 20, "90 RPM"));

        // 5mn N1/85rpm bem soft
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM NX UP"));

        // 5xs 5mn N4-55rpm a subir / 3mn N1-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N4_BLACK, 5, "55 RPM UP"));
        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "MAX UP"));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 3, "85 RPM"));
        fromStep = w.getTotalSteps() - 2;
        w.addStep(new WorkoutStepRepeat(fromStep, 5));

        // 5mn N1-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 5, "85 RPM"));

        // 15mn N4-90rpm
        w.addStep(new WorkoutStepZoneMinutes(N4_GREEN, 15, "90 RPM"));

        // 10mn N1-85rpm
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 10, "85 RPM"));
    }
}
