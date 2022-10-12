package fit.mts;

import fit.mts.workouts.B9;
import fit.mts.workouts.B13;
import fit.mts.workouts.B14;
import fit.mts.workouts.B9_Short;
import fit.mts.workouts.BXX_6M3X;
import fit.mts.workouts.FTP_Test;
import fit.mts.workouts.N3_15MX3;
import fit.mts.workouts.N3_20MX3;
import fit.mts.workouts.N5_10X3;
import fit.mts.workouts.N5_14X3;
import fit.mts.workouts.N5_2MX5;
import fit.mts.workouts.N5_2MX6;
import fit.mts.workouts.N5_30_30_5X3;
import fit.mts.workouts.N5_30_30_7X3;
import fit.mts.workouts.N5_30_30_8X3;
import fit.mts.workouts.N5_30_30_9X3;
import fit.mts.workouts.N5_30_30_10X3;
import fit.mts.workouts.N5_6X3;
import fit.mts.workouts.N5_7X3;
import fit.mts.workouts.N5_8X3;
import fit.mts.workouts.N5_9X3;
import fit.mts.workouts.N6_1MX9;
import fit.mts.workouts.PMA;

public class MTSSetGenerator {
    public static void main(String[] args) {
        Long id = 0L;

        WorkoutWriter.writeMTSGarminWorkout(new B9(++id));
        WorkoutWriter.writeMTSGarminWorkout(new B9_Short(++id));
        WorkoutWriter.writeMTSGarminWorkout(new B13(++id));
        WorkoutWriter.writeMTSGarminWorkout(new B14(++id));
        WorkoutWriter.writeMTSGarminWorkout(new BXX_6M3X(++id));
        WorkoutWriter.writeMTSGarminWorkout(new PMA(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_6X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_7X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_8X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_9X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_10X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_14X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_2MX5(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_2MX6(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N3_15MX3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N3_20MX3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new FTP_Test(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_30_30_5X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_30_30_7X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_30_30_8X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_30_30_9X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N5_30_30_10X3(++id));
        WorkoutWriter.writeMTSGarminWorkout(new N6_1MX9(++id));
    }
}
