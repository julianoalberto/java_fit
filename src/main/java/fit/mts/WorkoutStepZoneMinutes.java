package fit.mts;

import com.garmin.fit.WorkoutStepMesg;

import fit.model.WorkoutStep;
import fit.model.WorkoutStepHeartRate;

public class WorkoutStepZoneMinutes  implements WorkoutStep {
    private WorkoutStepHeartRate workoutStepHeartRate;

    public WorkoutStepZoneMinutes(Zone zone, int minutes, String stepLabel) {
        int duration = minutes * 60;
        this.workoutStepHeartRate = new WorkoutStepHeartRate(zone.heartRateMin, zone.heartRateMax, duration, stepLabel);        
    }

    public WorkoutStepMesg getWorkoutStepMesg() {
        return this.workoutStepHeartRate.getWorkoutStepMesg();
    }
}
