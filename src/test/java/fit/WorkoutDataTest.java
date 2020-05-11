package fit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;

import org.junit.Before;
import org.junit.Test;

import fit.model.Sport;
import fit.model.WorkoutData;
import fit.model.WorkoutStepHeartRate;

/**
 * Unit test for simple App.
 */
public class WorkoutDataTest 
{
    WorkoutData data;

    @Before
    public void setup() {
        data = new WorkoutData(Sport.CYCLING, "AA");
    }
    
    @Test
    public void isValidWorkoutNameTest() {
        assertTrue("Ss", WorkoutData.isValidWorkoutName("Ss"));
    } 
}
