package fit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.garmin.fit.File;
import com.garmin.fit.FileIdMesg;
import com.garmin.fit.GarminProduct;
import com.garmin.fit.Manufacturer;
import com.garmin.fit.Sport;
import com.garmin.fit.WorkoutMesg;

import org.junit.Before;
import org.junit.Test;

import fit.model.GarminWorkout;
import fit.model.Workout;
import fit.model.WorkoutFactory;
import fit.model.WorkoutStep;
import fit.model.WorkoutStepHeartRate;
import fit.model.WorkoutStepRepeat;

/**
 * Unit test for simple App.
 */
public class GarminWorkoutTest 
{
    Workout garmin;

    @Before
    public void setup() {
        garmin = WorkoutFactory.createWorkout(WorkoutFactory.Type.GARMIN_500_CYCLING);
        garmin.getWorkoutData().setWorkoutName("N5-3030X7");
    }

    @Test
    public void workoutInstance() {
        assert(garmin instanceof GarminWorkout);
    }

    @Test
    public void workoutFileIdMesgTest() {
        FileIdMesg msg = garmin.getWorkoutMetadata().getFileIdMesg();
        assertTrue(msg.getType().equals(File.WORKOUT));
        assertTrue(msg.getManufacturer().equals(Manufacturer.GARMIN));
        assertTrue(msg.getProduct().equals(GarminProduct.EDGE500));
        assertTrue(msg.getSerialNumber() == 0L);
        assertNotNull(msg.getTimeCreated());
    }

    @Test
    public void workoutMesgTest() {
        WorkoutMesg msg = garmin.getWorkoutData().getWorkoutMesg();
        assertEquals(msg.getSport(), Sport.CYCLING);
        assertTrue(msg.getNumValidSteps() == 0);
        assertEquals(msg.getWktName(), "N5-3030X7");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidNumSteps() {
        garmin.getWorkoutData().setNumValidSteps(-1);
    }

    @Test
    public void addStep() {
        garmin.addStep(new WorkoutStepHeartRate(135, 145, 30));
        assertEquals("num steps 1", 1, garmin.getTotalSteps());

        garmin.addStep(new WorkoutStepHeartRate(135, 145, 30));
        assertEquals("num steps 2", 2, garmin.getTotalSteps());        
    }   
    
    @Test
    public void getSteps()
    {
        garmin.addStep(new WorkoutStepHeartRate(135, 145, 30));
        garmin.addStep(new WorkoutStepRepeat(1, 2));

        assertEquals("steps size", 2, garmin.getSteps().size());
        
        
        WorkoutStep stp1 = garmin.getSteps().get(0);
        assertTrue("instance1", stp1 instanceof WorkoutStepHeartRate);

        WorkoutStep stp2 = garmin.getSteps().get(1);
        assertTrue("instance2", stp2 instanceof WorkoutStepRepeat);   
    }

    @Test
    public void getStepsIndex() {
        garmin.addStep(new WorkoutStepHeartRate(135, 145, 30));
        garmin.addStep(new WorkoutStepRepeat(1, 2));
        garmin.addStep(new WorkoutStepHeartRate(135, 145, 30));
        garmin.addStep(new WorkoutStepRepeat(1, 2));
        garmin.addStep(new WorkoutStepHeartRate(135, 145, 30));
        garmin.addStep(new WorkoutStepRepeat(1, 2));

        int i = 0;

        for (WorkoutStep s : garmin.getSteps()) {
            assertEquals(Integer.valueOf(i++), s.getWorkoutStepMesg().getMessageIndex());
        }
    }
}
