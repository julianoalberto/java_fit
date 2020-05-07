package fit.model;

import java.util.ArrayList;
import java.util.List;

public class GarminWorkout implements Workout {
    protected final WorkoutData data;
    protected final WorkoutMetadata metadata;
    protected final List<WorkoutStep> steps;

    public GarminWorkout(WorkoutMetadata metadata, WorkoutData data) {
        this.metadata = metadata;
        this.data = data;
        this.steps = new ArrayList<WorkoutStep>();
    }

    @Override
    public WorkoutData getWorkoutData() {
        return this.data;
    }

    @Override
    public WorkoutMetadata getWorkoutMetadata() {
        return this.metadata;
    }

    @Override
    public int getTotalSteps() {
        return steps.size();
    }

    @Override
    public void addStep(WorkoutStep step) {
        steps.add(step);
        data.setNumValidSteps(steps.size());
    }

    @Override
    public List<WorkoutStep> getSteps() {
        List<WorkoutStep> numberedSteps = new ArrayList<WorkoutStep>(steps);
        int i = 0;
        for (WorkoutStep step : numberedSteps) {
            step.getWorkoutStepMesg().setMessageIndex(i++);
        }
        return numberedSteps;
    }
}