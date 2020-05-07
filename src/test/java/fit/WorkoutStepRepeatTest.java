package fit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.garmin.fit.WktStepDuration;

import org.junit.Test;

import fit.model.WorkoutStepRepeat;

/**
 * Unit test for simple App.
 */
public class WorkoutStepRepeatTest 
{
    @Test
    public void workoutStepInstance() {
        WorkoutStepRepeat stp = new WorkoutStepRepeat(1, 10);
        assertNotNull("null", stp.getWorkoutStepMesg());
        assertEquals("type", stp.getWorkoutStepMesg().getDurationType(), WktStepDuration.REPEAT_UNTIL_STEPS_CMPLT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void repeatFromStepInvalid() {
        new WorkoutStepRepeat(0, 4);        
    }

    @Test(expected = IllegalArgumentException.class)
    public void repeatNumberRepsInvalid() {
        new WorkoutStepRepeat(1, 0);        
    }

    @Test
    public void repeatStepDataValidation() {
        WorkoutStepRepeat stp = new WorkoutStepRepeat(1, 1);
        assertEquals("fromStep", Long.valueOf(stp.getWorkoutStepMesg().getDurationValue()),  Long.valueOf(0L));
        stp.setFromStep(9);
        assertEquals("fromStep", Long.valueOf(stp.getWorkoutStepMesg().getDurationValue()),  Long.valueOf(8L));

        stp.setNumberOfRepetitions(1);
        assertEquals("numberOfRepetitions", Long.valueOf(stp.getWorkoutStepMesg().getTargetValue()),  Long.valueOf(1L));
    }
}
