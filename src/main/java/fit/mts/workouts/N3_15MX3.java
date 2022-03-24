package fit.mts.workouts;

import static fit.mts.Zone.N1_BLACK;
import static fit.mts.Zone.N2_BLACK;
import static fit.mts.Zone.N3_RED;
import static fit.mts.Zone.N5_BLACK;


import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;
import fit.mts.WorkoutStepZoneSeconds;
import fit.mts.Zone;

public class N3_15MX3 extends MTSGarminWorkout {
    public static final String NAME = "N3-15MX3";

    public N3_15MX3(Long workoutSerial) {
        /**
         * 2h30mn a 3h treino.
         * Aquecer 15mn a N1/85rpm.
         * De seguida fazer 3 series em plano de
         * 15mn a N3/75rpm com
         * 5mn de recuperação a N1 suave entre elas.
         * Depois podes finalizar o treino ou seguir até 3h no total rodando o resto a N1/N2 rpm livre.
         */
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);

        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 15, "85 RPM"));

        w.addStep(new WorkoutStepZoneMinutes(N3_RED, 15, "75 RPM"));
        w.addStep(new WorkoutStepZoneMinutes(N1_BLACK, 5, "85 RPM"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 3));

        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 105, "85 RPM"));
    }
}
