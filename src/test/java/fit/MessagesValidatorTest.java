package fit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fit.messages.WorkoutMessagesStructureValidator;
import fit.model.Workout;
import fit.model.WorkoutFactory;
import fit.model.WorkoutStepHeartRate;
import fit.model.WorkoutStepRepeat;

/**
 * Unit test for simple App.
 */
public class MessagesValidatorTest 
{
    Workout garmin;

    @Before
    public void setup() {
         garmin = WorkoutFactory.createWorkout(WorkoutFactory.Type.GARMIN_500_CYCLING);
         garmin.getWorkoutData().setWorkoutName("N5-3030X7");
         
         garmin.addStep(new WorkoutStepHeartRate(147, 153, 20, "85 RPM"));
         garmin.addStep(new WorkoutStepRepeat(1, 10));
         garmin.addStep(new WorkoutStepHeartRate(147, 153, 20, "85 RPM"));
         garmin.addStep(new WorkoutStepRepeat(3, 10));
         garmin.addStep(new WorkoutStepHeartRate(147, 153, 20, "85 RPM"));
         garmin.addStep(new WorkoutStepHeartRate(147, 153, 20, "85 RPM"));
    }

    @Test
    public void structureTest() {
        assertTrue(WorkoutMessagesStructureValidator.validate(garmin));
    }
}
