package fit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.garmin.fit.Mesg;

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
public class WorkoutMessagesStructureValidatorTest 
{
    Workout garmin;
    WorkoutStepRepeat repeat1, repeat2;
    WorkoutStepHeartRate step1, step2;

    @Before
    public void setup() {
         garmin = WorkoutFactory.createWorkout(WorkoutFactory.Type.GARMIN_500_CYCLING);
         garmin.getWorkoutData().setWorkoutName("N5-3030X7");
         repeat1 = new WorkoutStepRepeat(1, 1);
         repeat2 = new WorkoutStepRepeat(1, 1);
         step1 = new WorkoutStepHeartRate(50, 100, 1);
         step2 = new WorkoutStepHeartRate(50, 100, 2);
    }

    @Test
    public void missingStepMessages() {
        assertFalse("missingStepMessages false", WorkoutMessagesStructureValidator.validate(garmin.asMessages())); 

        garmin.addStep(step1);    
        assertTrue("missingStepMessages true", WorkoutMessagesStructureValidator.validate(garmin.asMessages()));
    }

    @Test
    public void validateFileIdMesg() {
        garmin.addStep(step1);
        List<Mesg> messages = garmin.asMessages();

        assertTrue("validateFileIdMesg true", WorkoutMessagesStructureValidator.validate(messages)); 

        messages.add(0, messages.get(1));
        assertFalse("validateFileIdMesg false", WorkoutMessagesStructureValidator.validate(messages)); 
    }

    @Test
    public void validateWorkoutMesg() {
        garmin.addStep(step1);
        List<Mesg> messages = garmin.asMessages();

        assertTrue("validateWorkoutMesg true", WorkoutMessagesStructureValidator.validate(messages)); 
        
        messages.add(1, messages.get(0));
        assertFalse("validateWorkoutMesg false", WorkoutMessagesStructureValidator.validate(messages)); 
    }

    @Test
    public void validateIfWorkoutStepMesgs() {
        garmin.addStep(step1);
        garmin.addStep(step2);
        garmin.addStep(repeat1);
        List<Mesg> messages = garmin.asMessages();

        assertTrue("validateIfWorkoutStepMesgs true", WorkoutMessagesStructureValidator.validate(messages)); 
        
        Mesg m = messages.get(0);
        messages.add(m);
        assertFalse("validateIfWorkoutStepMesgs false", WorkoutMessagesStructureValidator.validate(messages)); 
    }

    @Test
    public void validateStepMesgIndex() {
        garmin.addStep(step1);
        garmin.addStep(step2);
        garmin.addStep(repeat1);
        List<Mesg> messages = garmin.asMessages();
        
        assertTrue("validateStepMesgIndex true", WorkoutMessagesStructureValidator.validate(messages)); 
        
        repeat1.getWorkoutStepMesg().setMessageIndex(0);
        assertFalse("validateStepMesgIndex false", WorkoutMessagesStructureValidator.validate(messages)); 
    }

    @Test
    public void validateFromStep() {
        garmin.addStep(step1);
        garmin.addStep(step2);
        garmin.addStep(repeat1);

        repeat2.setFromStep(9999);
        garmin.addStep(repeat2);
        List<Mesg> messages = garmin.asMessages();

        assertFalse("validateFromStep false", WorkoutMessagesStructureValidator.validate(messages));
        
        messages.remove(messages.size() - 1);
        assertTrue("validateFromStep true", WorkoutMessagesStructureValidator.validate(messages));
    }

    @Test
    public void validateRepeatOverlap() {
        garmin.addStep(step1);
        garmin.addStep(new WorkoutStepRepeat(1, 3));
        garmin.addStep(step2);

        assertTrue("validateFromStep true", WorkoutMessagesStructureValidator.validate(garmin.asMessages()));

        garmin.addStep(new WorkoutStepRepeat(1, 3));
        assertFalse("validateFromStep false", WorkoutMessagesStructureValidator.validate(garmin.asMessages()));
    }
}
