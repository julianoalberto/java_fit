package fit.model;

import java.util.regex.Pattern;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;

public class WorkoutStepHeartRate extends AbstractWorkoutStep {
    public static final int HEART_RATE_ADDITIONAL_VALUE = 100;
    public static final int MIN_HEART_RATE = 50;
    public static final int MAX_HEART_RATE = 250;
    public static final int MIN_TIME = 1;
    
    private static Pattern PATTERN;
    static {
        PATTERN = Pattern.compile("[A-Za-z0-9\\-\\s]{1,15}");
    }

    private int minHeartRate;
    private int maxHeartRate;
    private int duration;
    private String stepLabel;

    
    private WorkoutStepHeartRate() {
        super();
        workoutStepMesg = new WorkoutStepMesg();
        workoutStepMesg.setIntensity(Intensity.ACTIVE);
        workoutStepMesg.setDurationType(WktStepDuration.TIME);
        workoutStepMesg.setTargetType(WktStepTarget.HEART_RATE);
        workoutStepMesg.setTargetValue(0L);
    }

    public WorkoutStepHeartRate(int minHeartRate, int maxHeartRate, int duration, String stepLabel) {
        this();
        setMinHeartRate(minHeartRate);
        setMaxHeartRate(maxHeartRate);
        setDuration(duration);
        setStepLabel(stepLabel);
    }

    public WorkoutStepHeartRate(int minHeartRate, int maxHeartRate, int duration) {
        this(minHeartRate, maxHeartRate, duration, null);
    }

    public int getMinHeartRate() {
        return minHeartRate;
    }

    public void setMinHeartRate(int minHeartRate) {
        if (!isValidHeartRate(minHeartRate)) {
            throw new IllegalArgumentException("invalid heart rate: " + minHeartRate);
        }
        this.minHeartRate = minHeartRate;
        workoutStepMesg.setCustomTargetHeartRateLow(normalizeHeartRate(minHeartRate));
    }

    public int getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(int maxHeartRate) {
        if (!isValidHeartRate(maxHeartRate)) {
            throw new IllegalArgumentException("invalid heart rate: " + maxHeartRate);
        }
        this.maxHeartRate = maxHeartRate;
        workoutStepMesg.setCustomTargetHeartRateHigh(normalizeHeartRate(maxHeartRate));
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (!isValidDuration(duration)) {
            throw new IllegalArgumentException("invalid duration: " + duration);            
        }
        this.duration = duration;
        workoutStepMesg.setDurationValue(convertDuration(duration));       
    }

    public String getStepLabel() {
        return stepLabel;
    }

    public void setStepLabel(String stepLabel) {
        if (stepLabel == null) {
            stepLabel = getDefaultStepLabel();
        }

        if (!isValidStepLabel(stepLabel)) {
            throw new IllegalArgumentException("invalid step label: " + stepLabel);
        }

        this.stepLabel = stepLabel;
        workoutStepMesg.setWktStepName(stepLabel);
    }

    public static Long convertDuration(int duration) {
        return Long.valueOf(duration * 1000);
    }

    public static boolean isValidDuration(int duration) {
        return !(duration < MIN_TIME);
    }

    public static Long normalizeHeartRate(int heartRate) {
        return Long.valueOf(heartRate + HEART_RATE_ADDITIONAL_VALUE);
    }

    public static boolean isValidHeartRate(int heartRate) {
        return (heartRate >= MIN_HEART_RATE && heartRate <= MAX_HEART_RATE);
    }

	public static boolean isValidStepLabel(String stepLabel) {
        if (stepLabel == null) {
            return false;
        }
        
        return PATTERN.matcher(stepLabel).matches();
    }
    
    private String getDefaultStepLabel() {
        return getMinHeartRate() + "-" + getMaxHeartRate() +
            " " + getDuration() + "s";
    }
}