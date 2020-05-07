package fit.model;

import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WorkoutStepMesg;

public class WorkoutStepRepeat extends AbstractWorkoutStep {
    private int fromStep;
    private int numberOfRepetitions;

    /**
     * 
     * @param fromStep 1 based step number
     * @param numberOfRepetitions
     */
    public WorkoutStepRepeat(int fromStep, int numberOfRepetitions) {
        super();
        workoutStepMesg =  new WorkoutStepMesg();
        getWorkoutStepMesg().setDurationType(WktStepDuration.REPEAT_UNTIL_STEPS_CMPLT);
        setFromStep(fromStep);
        setNumberOfRepetitions(numberOfRepetitions);
    }

    public int getFromStep() {
        return fromStep;
    }

    public void setFromStep(int fromStep) {
        if (fromStep < 1) {
            throw new IllegalArgumentException("fromStep cannot be lower than 1: " + fromStep);
        }
        this.fromStep = fromStep;
        // fromStep is 0-based in Garmin SDK but 1-based here.
        this.getWorkoutStepMesg().setDurationValue(Integer.toUnsignedLong(fromStep - 1));
    }

    public int getNumberOfRepetitions() {
        return numberOfRepetitions;
    }

    public void setNumberOfRepetitions(int numberOfRepetitions) {
        if (numberOfRepetitions < 1) {
            throw new IllegalArgumentException("numberOfRepetitions cannot be lower than 1: " + fromStep);
        }
        this.numberOfRepetitions = numberOfRepetitions;
        this.getWorkoutStepMesg().setTargetValue(Integer.toUnsignedLong(numberOfRepetitions));
    }
}