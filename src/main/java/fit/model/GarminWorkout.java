package fit.model;

import java.util.ArrayList;
import java.util.List;

import com.garmin.fit.Mesg;

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
    public Workout addStep(WorkoutStep step) {
        steps.add(step);
        data.setNumValidSteps(steps.size());
        return this;
    }

    @Override
    public List<WorkoutStep> getSteps() {
        List<WorkoutStep> indexedSteps = new ArrayList<WorkoutStep>(steps);
        
        int i = 0;
        for (WorkoutStep step : indexedSteps) {
            step.getWorkoutStepMesg().setMessageIndex(i);
            i++;
        }
        
        return indexedSteps;
    }

    @Override
    public List<Mesg> asMessages() {
        ArrayList<Mesg> messages = new ArrayList<Mesg>();
        messages.add(getWorkoutMetadata().getFileIdMesg());
        messages.add(getWorkoutData().getWorkoutMesg());

        List<WorkoutStep> stepMessages = getSteps();

        for (WorkoutStep stp : stepMessages) {
            messages.add(stp.getWorkoutStepMesg());
        }

        return messages;
    }
}