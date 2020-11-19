package fit.mts;

import fit.mts.workouts.B9;
import fit.mts.workouts.PMA;

public class MTSSetGenerator {
    public static void main(String[] args) {
        Long id = 0L;

        WorkoutWriter.writeMTSGarminWorkout(new B9(++id));
        WorkoutWriter.writeMTSGarminWorkout(new PMA(++id));
    }
}
