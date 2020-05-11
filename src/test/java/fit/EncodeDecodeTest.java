package fit;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import com.garmin.fit.FileEncoder;
import com.garmin.fit.Fit;
import com.garmin.fit.FitRuntimeException;
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
public class EncodeDecodeTest 
{
    /**
     * name "N5-3030X10"
     * 300s 137 143 "85 RPM"
     *  30s 172 178 "N5"
     *  30s 132 138
     *  Repeat previous 2 10 times
     * 300s 132 138
     */

    Workout garmin;
    WorkoutStepHeartRate warmup, active, recover, coolDown;
    WorkoutStepRepeat repeat;
    
    @Before
    public void setup() {
        garmin = WorkoutFactory.createWorkout(WorkoutFactory.Type.GARMIN_500_CYCLING);
        garmin.getWorkoutData().setWorkoutName("N5-3030X10");
    
        warmup = new WorkoutStepHeartRate(137, 143, 300, "85 RPM");
        active = new WorkoutStepHeartRate(172, 178, 30, "N5");
        recover = new WorkoutStepHeartRate(132, 138, 30);
        repeat = new WorkoutStepRepeat(2, 10);
        coolDown = new WorkoutStepHeartRate(132, 138, 300);

        garmin.addStep(warmup)
            .addStep(active)
            .addStep(recover)
            .addStep(repeat)
            .addStep(coolDown);
    }

    @Test
    public void encodeDecode() {
        FileEncoder encoder;
        String filePath = "0000_workout.fit";

        try {
            System.out.printf("Encoding %s in %s\n", garmin.getWorkoutData().getWorkoutName(), filePath);
            encoder = new FileEncoder(new File(filePath), Fit.ProtocolVersion.V1_0);
            List<Mesg> messages = garmin.asMessages();
            assertTrue("steps valid", WorkoutMessagesStructureValidator.validate(messages));
            encoder.write(messages);        
            encoder.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.printf(e.getMessage());
        }
    }

    @Test
    public void testWorkout() {
        FileEncoder encoder;
        String filePath = "000000_workout.fit";


        garmin = WorkoutFactory.createWorkout(WorkoutFactory.Type.GARMIN_500_CYCLING);
        garmin.getWorkoutData().setWorkoutName("N5-3030x10");
    
        warmup = new WorkoutStepHeartRate(137, 143, 15, "85 rpm");
        active = new WorkoutStepHeartRate(172, 178, 10, "N5");
        recover = new WorkoutStepHeartRate(132, 138, 10);
        repeat = new WorkoutStepRepeat(2, 3);
        coolDown = new WorkoutStepHeartRate(132, 138, 20);

        garmin.addStep(warmup)
            .addStep(active)
            .addStep(recover)
            .addStep(repeat)
            .addStep(coolDown);




        try {
            System.out.printf("Encoding %s in %s\n", garmin.getWorkoutData().getWorkoutName(), filePath);
            encoder = new FileEncoder(new File(filePath), Fit.ProtocolVersion.V1_0);

            garmin.getWorkoutMetadata().setSerialnumber(12369812L);
            List<Mesg> messages = garmin.asMessages();
            assertTrue("steps valid", WorkoutMessagesStructureValidator.validate(messages));
            encoder.write(messages);        
            encoder.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.printf(e.getMessage());
        }
    }
}
