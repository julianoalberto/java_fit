package fit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;

import org.junit.Before;
import org.junit.Test;

import fit.model.WorkoutStepHeartRate;

/**
 * Unit test for simple App.
 */
public class WorkoutStepHearRateTest 
{
    WorkoutStepHeartRate stp;

    @Before
    public void setup() {
        stp = new WorkoutStepHeartRate(100, 120, 1);
    }
    
    @Test
    public void defaultData() {
        WorkoutStepMesg msg = stp.getWorkoutStepMesg();

        assertNotNull("null", msg);
        assertEquals("intensity", Intensity.ACTIVE, msg.getIntensity());
        assertEquals("durationType", WktStepDuration.TIME, msg.getDurationType());
        assertEquals("targetType", WktStepTarget.HEART_RATE, msg.getTargetType());
        assertEquals("targetValue", Long.valueOf(0L), msg.getTargetValue());        
    } 
    
    @Test
    public void partialConstructorTest() {
        WorkoutStepHeartRate  stp = new WorkoutStepHeartRate(150, 160, 30);
        WorkoutStepMesg msg = stp.getWorkoutStepMesg();

        assertNotNull("null", msg);
        assertEquals("intensity", Intensity.ACTIVE, msg.getIntensity());
        assertEquals("durationType", WktStepDuration.TIME, msg.getDurationType());
        assertEquals("targetType", WktStepTarget.HEART_RATE, msg.getTargetType());
        assertEquals("targetValue", Long.valueOf(0L), msg.getTargetValue());

        assertEquals("min hr value", Long.valueOf(250), Long.valueOf(msg.getCustomTargetHeartRateLow()));
        assertEquals("max hr value", Long.valueOf(260), Long.valueOf(msg.getCustomTargetHeartRateHigh()));
        
        assertEquals("seconds", Long.valueOf(30000L), Long.valueOf(msg.getDurationValue()));

        assertNull("label null", msg.getWktStepName());
        
    }

    @Test
    public void completeConstructorTest() {
        WorkoutStepHeartRate  stp = new WorkoutStepHeartRate(150, 160, 30, "85 RPM");
        WorkoutStepMesg msg = stp.getWorkoutStepMesg();

        assertNotNull("null", msg);
        assertEquals("intensity", Intensity.ACTIVE, msg.getIntensity());
        assertEquals("durationType", WktStepDuration.TIME, msg.getDurationType());
        assertEquals("targetType", WktStepTarget.HEART_RATE, msg.getTargetType());
        assertEquals("targetValue", Long.valueOf(0L), msg.getTargetValue());

        assertEquals("min hr value", Long.valueOf(250), Long.valueOf(msg.getCustomTargetHeartRateLow()));
        assertEquals("max hr value", Long.valueOf(260), Long.valueOf(msg.getCustomTargetHeartRateHigh()));
        
        assertEquals("seconds", Long.valueOf(30000L), Long.valueOf(msg.getDurationValue()));

        assertNotNull("label not null", msg.getWktStepName());
        assertEquals("label value", "85 RPM", msg.getWktStepName());        
    }

    @Test(expected = IllegalArgumentException.class)
    public void durationInvalid() {
        stp.setDuration(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void minHeartRateLower() {
        stp.setMinHeartRate(WorkoutStepHeartRate.MIN_HEART_RATE - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void maxHeartRateLower() {
        stp.setMaxHeartRate(WorkoutStepHeartRate.MIN_HEART_RATE - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void minHeartRateHigher() {
        stp.setMinHeartRate(WorkoutStepHeartRate.MAX_HEART_RATE + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void maxHeartRateHigher() {
        stp.setMaxHeartRate(WorkoutStepHeartRate.MAX_HEART_RATE + 1);
    }
    
    @Test
    public void validateStepLabel() {
        assertTrue("blank", WorkoutStepHeartRate.isValidStepLabel(""));
        assertTrue("null", WorkoutStepHeartRate.isValidStepLabel(null));
        assertTrue("letter", WorkoutStepHeartRate.isValidStepLabel("A"));
        assertTrue("digit", WorkoutStepHeartRate.isValidStepLabel("0"));
        assertTrue("space", WorkoutStepHeartRate.isValidStepLabel(" "));
        assertFalse("longer", WorkoutStepHeartRate.isValidStepLabel("AAAAAAAAAAAAAAAAAAA"));
        assertFalse("invalid char", WorkoutStepHeartRate.isValidStepLabel("X-0"));
        assertTrue("valid", WorkoutStepHeartRate.isValidStepLabel("85 RPM UP"));        
    }

    @Test(expected = IllegalArgumentException.class)
    public void stepLabelInvalid() {
        stp.setStepLabel("A-BC");
    }    
}
