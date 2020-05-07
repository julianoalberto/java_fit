package fit.model;

import com.garmin.fit.WorkoutStepMesg;

public class AbstractWorkoutStep implements WorkoutStep {
    protected WorkoutStepMesg workoutStepMesg;

    @Override
    public WorkoutStepMesg getWorkoutStepMesg() {
        return this.workoutStepMesg;
    }
}