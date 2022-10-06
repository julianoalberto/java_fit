package fit.mts.workouts;

import fit.model.GarminWorkout;
import fit.model.WorkoutFactory;

public abstract class MTSGarminWorkout {
    protected GarminWorkout w;

    protected MTSGarminWorkout() {
        this.w = (GarminWorkout) WorkoutFactory.createWorkout(WorkoutFactory.Type.GARMIN_500_CYCLING);
    }

    public GarminWorkout getWorkout() {
        return w;
    }
}
