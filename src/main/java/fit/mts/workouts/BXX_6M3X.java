package fit.mts.workouts;

import static fit.mts.Zone.*;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;
import fit.mts.WorkoutStepZoneSeconds;

public class BXX_6M3X extends MTSGarminWorkout {
    public static final String NAME = "BXX-6M3X";

    public BXX_6M3X(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);
        /**
         * 2h treino.
         * Aquecer 30mn progressivamente até N2.
         * Em subida 3xs 6mn
         * 4mn de 30seg N5
         * 30seg N1 seguido
         * 2mn a N4 green
         * Recuperar 5mn pedalando suave entre cada serie.
         * Finalizar a N1/N2. Alongamento no final.
         */

        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 30, "85 RPM NX UP"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "N5 UP"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 30, "N1 UP"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));
        w.addStep(new WorkoutStepZoneMinutes(N4_GREEN, 2, "UP"));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "N5 UP"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 30, "N1 UP"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));
        w.addStep(new WorkoutStepZoneMinutes(N4_GREEN, 2, "UP"));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "N5 UP"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 30, "N1 UP"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 4));
        w.addStep(new WorkoutStepZoneMinutes(N4_GREEN, 2, "UP"));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM"));

        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 120, "90 RPM"));
    }
}
