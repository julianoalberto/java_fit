package fit.mts;

import com.garmin.fit.WorkoutStepMesg;

import fit.model.WorkoutStep;
import fit.model.WorkoutStepHeartRate;

public class WorkoutStepZoneSeconds  implements WorkoutStep {
    private WorkoutStepHeartRate workoutStepHeartRate;

    public WorkoutStepZoneSeconds(Zone zone, int seconds, String stepLabel) {
        this.workoutStepHeartRate = new WorkoutStepHeartRate(zone.heartRateMin, zone.heartRateMax, seconds, stepLabel);        
    }

    public WorkoutStepMesg getWorkoutStepMesg() {
        return this.workoutStepHeartRate.getWorkoutStepMesg();
    }
}
