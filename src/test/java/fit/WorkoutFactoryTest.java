package fit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.garmin.fit.File;
import com.garmin.fit.FileIdMesg;
import com.garmin.fit.GarminProduct;
import com.garmin.fit.Manufacturer;
import com.garmin.fit.Sport;
import com.garmin.fit.WorkoutMesg;

import org.junit.Test;

import fit.model.GarminWorkout;
import fit.model.Workout;
import fit.model.WorkoutData;
import fit.model.WorkoutFactory;

/**
 * Unit test for simple App.
 */
public class WorkoutFactoryTest 
{
    Workout wkt = WorkoutFactory.createWorkout(WorkoutFactory.Type.GARMIN_500_CYCLING);

    @Test
    public void workoutInstance() {
        assert(wkt instanceof GarminWorkout);
    }

    @Test
    public void workoutFileIdMesgTest() {
        FileIdMesg msg = wkt.getWorkoutMetadata().getFileIdMesg();
        assertTrue(msg.getType().equals(File.WORKOUT));
        assertTrue(msg.getManufacturer().equals(Manufacturer.GARMIN));
        assertTrue(msg.getProduct().equals(GarminProduct.EDGE500));
        assertTrue(msg.getSerialNumber() == 0L);
        assertNotNull(msg.getTimeCreated());
    }

    @Test
    public void workoutMesgTest() {
        WorkoutMesg msg = wkt.getWorkoutData().getWorkoutMesg();
        assertEquals(msg.getSport(), Sport.CYCLING);
        assertTrue(msg.getNumValidSteps() == 0);
        assertEquals(msg.getWktName(), "EDGE-500");
    }

    @Test
    public void workoutNameValidationTest() {
        assertFalse("null", WorkoutData.isValidWorkoutName(null)); // null
        assertFalse("blank", WorkoutData.isValidWorkoutName("")); // blank
        assertTrue("shorter", WorkoutData.isValidWorkoutName("AAAAAAA")); // shorter length
        assertTrue("exact", WorkoutData.isValidWorkoutName("AAAAAAAAAAAAAA")); // exact length
        assertFalse("longer", WorkoutData.isValidWorkoutName("AAAAAAAAAAAAAAAAAAAAA")); // longer length

        assertFalse("lower case", WorkoutData.isValidWorkoutName("a"));
        assertTrue("upper case", WorkoutData.isValidWorkoutName("ABC"));

        assertTrue("digit", WorkoutData.isValidWorkoutName("0"));
        assertTrue("letter digit", WorkoutData.isValidWorkoutName("A0"));
        assertTrue("digit letter", WorkoutData.isValidWorkoutName("0A"));

        assertTrue("dash", WorkoutData.isValidWorkoutName("-"));
        assertTrue("letter dash letter", WorkoutData.isValidWorkoutName("A-B"));
        assertTrue("letter dash digit", WorkoutData.isValidWorkoutName("A-9"));

        assertTrue("valid name", WorkoutData.isValidWorkoutName("PMA-3030X10"));
        assertFalse("invalid name", WorkoutData.isValidWorkoutName("PMA-3030x10"));
    }
}
