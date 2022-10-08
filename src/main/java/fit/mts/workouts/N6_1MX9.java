package fit.mts.workouts;

import static fit.mts.Zone.*;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;

public class N6_1MX9 extends MTSGarminWorkout {
    public static final String NAME = "N6-1MX9";

    public N6_1MX9(Long workoutSerial) {
        /*
         * 3h a 3h30mn treino
         * Aquecer 30mn progressivamente até N2.
         * Depois fazer em subida ligeira 9xs 1mn no teu máximo
         * (tudo em rpm livre de velocidade/power).
         * Recuperar 5mn pedalando suave entre cada serie.
         * Finalizar a N2 com algumas subidas a N3.
         * Alongamento no final.
         */
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 30, "85 RPM"));

        w.addStep(new WorkoutStepZoneMinutes(N6_BLACK, 1, "MAX"));
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 5, "85 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 9));

        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 120, "85 RPM"));
    }
}
