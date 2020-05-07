package fit.model;

import java.util.Date;

public class WorkoutFactory {
    
    public static Workout createWorkout(Type workoutType) {
        switch(workoutType) {
            case GARMIN_500_CYCLING:
                return createGarmin500Workout();
            default:
                return defaultWorkout();
        }
    }

    private static Workout defaultWorkout() {
        return null;
    }

    private static Workout createGarmin500Workout() {
        WorkoutMetadata metadata = new WorkoutMetadata(0L, Manufacturer.GARMIN, Product.GARMIN_EDGE_500, new Date());
        WorkoutData data = new WorkoutData(Sport.CYCLING, "EDGE-500");
        return new GarminWorkout(metadata, data);
    }

    public static enum Type {
        GARMIN_500_CYCLING
    }
}