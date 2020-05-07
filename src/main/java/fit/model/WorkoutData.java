package fit.model;

import java.util.regex.Pattern;

import com.garmin.fit.WorkoutMesg;

public class WorkoutData {
    private static Pattern PATTERN;
    static {
        PATTERN = Pattern.compile("[A-Z0-9\\-]{1,15}");
    }

    private WorkoutMesg workoutMesg;
    private Sport sport;

    public WorkoutData(Sport sport, String workoutName) {
        workoutMesg = new WorkoutMesg();
        setSport(sport);
        setNumValidSteps(0);
        setWorkoutName(workoutName);
    }

	public WorkoutMesg getWorkoutMesg() {
		return workoutMesg;
    }
    
    public void setSport(Sport sport) {
        this.sport = sport;
        getWorkoutMesg().setSport(sport.getValue());
    }

    public Sport getSport() {
        return this.sport;
    }

    public void setNumValidSteps(int validNumSteps) {
        if (!isValidNumValidSteps(validNumSteps)) {
            throw new IllegalArgumentException("invalid number of steps: " + validNumSteps);
        }
        getWorkoutMesg().setNumValidSteps(validNumSteps);
    }

    public Integer getNumValidSteps() {
        return getWorkoutMesg().getNumValidSteps();
    }

    public void setWorkoutName(String workoutName) throws IllegalArgumentException {
        if (!isValidWorkoutName(workoutName)) {
            throw new IllegalArgumentException("invalid workout name: " + workoutName);
        }
        getWorkoutMesg().setWktName(workoutName.toUpperCase()); 
    }

    public String getWorkoutName() {
        return getWorkoutMesg().getWktName();
    }
    
    public static boolean isValidWorkoutName(String workoutName) {
        if (workoutName == null || workoutName.isEmpty()) {
            return false;
        }

        return PATTERN.matcher(workoutName).matches();
    }

    public static boolean isValidNumValidSteps(int numValidSteps) {
        return numValidSteps >= 0;
    }
}