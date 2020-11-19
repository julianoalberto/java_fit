package fit.mts.workouts;

import static fit.mts.Zone.*;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;
import fit.mts.WorkoutStepZoneSeconds;
import fit.mts.Zone;

public class PMA extends MTSGarminWorkout {
    public static final String NAME = "PMA";

    public PMA(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        //2h de treino no alcatrão na bike de competição em falso 
        // 30mn a N1-N2/95rpm
        //N1/N2-95rpm. Alongamentos no final. 

        // 30mn a N1-N2/95rpm
        w.addStep(new WorkoutStepZoneMinutes(N1_RED, 30, "95 RPM"));
        
        // bloco 1
        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 40, "80 RPM"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 20, "80 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 1, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 40, "80 RPM"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 20, "80 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 1, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 40, "80 RPM"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 20, "80 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 7, "85 RPM"));

        // bloco 2
        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "85 RPM"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 30, "85 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 1, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "85 RPM"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 30, "85 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 1, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "85 RPM"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 30, "85 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 7, "85 RPM"));

        // bloco 3
        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 20, "90 RPM"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 40, "90 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 1, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 20, "90 RPM"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 40, "90 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 1, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 20, "90 RPM"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 40, "90 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));

        w.addStep(new WorkoutStepZoneMinutes(N1_RED, 30, "95 RPM"));        
    }    
}
