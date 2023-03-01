package fit.mts.workouts;

import static fit.mts.Zone.N1_BLACK;
import static fit.mts.Zone.N1_GREEN;
import static fit.mts.Zone.N1_RED;
import static fit.mts.Zone.N2_BLACK;
import static fit.mts.Zone.N5_GREEN;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;

public class FTP_Pre_Test extends MTSGarminWorkout {
    public static final String NAME = "FTP-PRE";

    public FTP_Pre_Test(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        // 20mn N1 black
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 20, "85 RPM"));

        // 5mn N2 black
        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 5, "85 RPM"));

        // 1mn N5 green
        // 5mn N1 red
        w.addStep(new WorkoutStepZoneMinutes(N5_GREEN, 1, "95 RPM"));
        w.addStep(new WorkoutStepZoneMinutes(N1_RED, 5, "85 RPM"));
        int fromStep = w.getTotalSteps() - 1;
        w.addStep(new WorkoutStepRepeat(fromStep, 2));

        // 10mn N1 green
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 10, "85 RPM"));
    }
}
