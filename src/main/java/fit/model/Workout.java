package fit.model;

import java.util.List;

import com.garmin.fit.Mesg;

public interface Workout {
    public WorkoutData getWorkoutData();

    public WorkoutMetadata getWorkoutMetadata();

    public int getTotalSteps();

    public void addStep(WorkoutStep step);

    public List<WorkoutStep> getSteps();

    public List<Mesg> asMessages();
}