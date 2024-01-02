package fit.mts;

import fit.mts.workouts.B1;
import fit.mts.workouts.B2;
import fit.mts.workouts.B3;
import fit.mts.workouts.FTP_Pre_Test;
import fit.mts.workouts.FTP_Test;
import fit.mts.workouts.N2_120M;
import fit.mts.workouts.N5_30_30_5X3;
import fit.mts.workouts.N5_30_30_7X3;
import fit.mts.workouts.N5_30_30_8X3;
import fit.mts.workouts.N5_30_30_9X3;
import fit.mts.workouts.PMA;

public class MTSSetGenerator {
    public static void main(String[] args) {
        Long id = 0L;

        WorkoutWriter.writeMTSGarminWorkout(new B1(++id));
        WorkoutWriter.writeMTSGarminWorkout(new B2(++id));
        WorkoutWriter.writeMTSGarminWorkout(new B3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N2_120M(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_30_30_5X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_30_30_7X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_30_30_8X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_30_30_9X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new PMA(++id));
        WorkoutWriter.writeMTSGarminWorkout(new FTP_Pre_Test(++id));
        WorkoutWriter.writeMTSGarminWorkout(new FTP_Test(++id));

        // WorkoutWriter.writeMTSGarminWorkout(new B9_Short(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new B14(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new BXX_6M3X(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N5_6X3(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N5_7X3(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N5_8X3(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N5_9X3(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N5_10X3(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N5_14X3(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N5_2MX5(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N5_2MX6(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N3_15MX3(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N3_20MX3(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new FTP_Pre_Test(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new FTP_Test(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N5_30_30_8X3(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N5_30_30_9X3(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N5_30_30_10X3(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N6_1MX9(++id));
        // WorkoutWriter.writeMTSGarminWorkout(new N2_N3_10M(++id));
    }
}
