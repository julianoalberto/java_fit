package fit.mts.workouts;

import static fit.mts.Zone.N1_BLACK;
import static fit.mts.Zone.N1_GREEN;
import static fit.mts.Zone.N2_BLACK;
import static fit.mts.Zone.N5_BLACK;

import fit.model.WorkoutStepRepeat;
import fit.mts.WorkoutStepZoneMinutes;
import fit.mts.WorkoutStepZoneSeconds;

public class N5_6X3 extends MTSGarminWorkout {
    public static final String NAME = "N5-6x3";

    public N5_6X3(Long workoutSerial) {
        super();
        w.getWorkoutMetadata().setSerialnumber(workoutSerial);
        w.getWorkoutData().setWorkoutName(NAME);


        /* 2h treino. Aquecer 45mn progressivamente até N2 fazendo 
        6 sprints lançados de intensidade máxima de 6seg a partir dos 
        10mn um a cada 5mn. 
        A partir dos 45mn de treino deverás fazer 
        3 series de 6xs seguidas e ininterruptas de 30seg a N5 (quase no máximo) 
        seguido de 15seg a N1 - 
        5mn a N1 green entre cada serie. 
        Depois segue o normal treino onde todas as zonas a rolar e a 
        subir devem respeitar um teto máximo de N2, devendo rodar nestas 
        partes entre PRECISAMENTE NO INTERVALO DA ZONA N2. Cadência alta, 
        procurando sempre rodar para cima de 90rpm nas fases a subir e a rolar. 
        Alongamentos no final. */

        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 45, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "N5"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 15, "N1"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 6));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "N5"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 15, "N1"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 6));
        w.addStep(new WorkoutStepZoneMinutes(N1_GREEN, 5, "85 RPM"));

        w.addStep(new WorkoutStepZoneSeconds(N5_BLACK, 30, "N5"));
        w.addStep(new WorkoutStepZoneSeconds(N1_BLACK, 15, "N1"));
        w.addStep(new WorkoutStepRepeat(w.getTotalSteps() - 1, 6));

        w.addStep(new WorkoutStepZoneMinutes(N2_BLACK, 45, "90 RPM"));
    }    
}
