package fit.mts.workouts;

import static fit.mts.Zone.N1_BLACK;
import static fit.mts.Zone.N1_GREEN;
import static fit.mts.Zone.N2_BLACK;
import static fit.mts.Zone.N5_GREEN;

import fit.mts.WorkoutStepZoneMinutes;
import fit.mts.WorkoutStepZoneSeconds;

public class FTP_Pre_Test extends MTSGarminWorkout {
    public static final String NAME = "FTP-PRE";

    public FTP_Pre_Test(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 15, "85 RPM"));
        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 5, "85 RPM"));
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 5, "85 RPM"));
        w.addStep(new WorkoutStepZoneSeconds(N5_GREEN, 30, "95 RPM"));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM"));
    }
}
