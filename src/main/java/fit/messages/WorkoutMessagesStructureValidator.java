package fit.messages;

import java.util.Iterator;
import java.util.List;

import com.garmin.fit.FileIdMesg;
import com.garmin.fit.Mesg;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WorkoutMesg;
import com.garmin.fit.WorkoutStepMesg;

public class WorkoutMessagesStructureValidator {
    public static final String INVALID_MINNIMUM_MESGS = "must have at least 3 Mesgs: FileIdMesg, WorkoutMesg and one or more WorkoutStepMesg";
    public static final String INVALID_FILE_ID_MESG = "first message is not FileIdMesg";
    public static final String INVALID_WORKOUT_MESG = "first message is not WorkoutMesg";
    public static final String INVALID_MESG_IN_STEPS = "invalid Mesg instance in steps list";
    public static final String INVALID_STEP_INDEX = "step index not sorted";
    public static final String INVALID_STEP_REPEAT = "step repeat invalid";
    public static final String INVALID_FROM_STEP_TEMPLATE = "invalid fromStep %s at step %s\n";
    public static final String INVALID_REPEAT_OVERLAP_TEMPLATE = "repeat %s is overlaping repeat %s";

    public static boolean validate(List<Mesg> messages) {
        return startValidationchain(messages);        
    }    
    
    private static boolean startValidationchain(List<Mesg> messages) {
        return validateMinnimumMesgs(messages);
    }
    private static boolean validateMinnimumMesgs(List<Mesg> messages) {
        if (messages.size() < 3) {
            System.out.println(INVALID_MINNIMUM_MESGS);
            return false;
        }
        return validateFileIdMesg(messages);
    }

    private static boolean validateFileIdMesg(List<Mesg> messages) {
        if (!(messages.get(0) instanceof FileIdMesg)) {
            System.out.println(INVALID_FILE_ID_MESG);
            return false;
        }
        return validateWorkoutMesg(messages);
    }

    private static boolean validateWorkoutMesg(List<Mesg> messages) {
        if (!(messages.get(1) instanceof WorkoutMesg)) {
            System.out.println(INVALID_WORKOUT_MESG);
            return false;
        }
        return validateIfWorkoutStepMesgs(messages);
    }

    private static boolean validateIfWorkoutStepMesgs(List<Mesg> messages) {
        Iterator<Mesg> it = messages.subList(2, messages.size()).iterator();
        
        while (it.hasNext()) {
            Mesg m = it.next();

            if (!(m instanceof WorkoutStepMesg)) {
                System.out.println(INVALID_MESG_IN_STEPS);
                return false;
            }
        }
       
        return validateStepMesgIndex(messages);
    }

    private static boolean validateStepMesgIndex(List<Mesg> messages) {
        Iterator<Mesg> it = messages.subList(2, messages.size()).iterator();

        int i = 0;

        while (it.hasNext()) {
            if (((WorkoutStepMesg) it.next()).getMessageIndex() != i++) {
                System.out.println(INVALID_STEP_INDEX);
                return false;
            }
        }

        return validateFromStep(messages);
    }

    /**
     * Validates that repeat steps reference valid from steps.
     */
    private static boolean validateFromStep(List<Mesg> messages) {
        Iterator<Mesg> it = messages.subList(2, messages.size()).iterator();

        while (it.hasNext()) {
            WorkoutStepMesg m = (WorkoutStepMesg) it.next();

            if (isRepeatStepMesg(m)) {
                long fromStep = m.getDurationValue();
                if (fromStep >= m.getMessageIndex()) {
                    System.out.printf(INVALID_FROM_STEP_TEMPLATE, fromStep, m.getMessageIndex());
                    return false;
                }
            }
        }
       
        return validateRepeatOverlap(messages);
    }

    /**
     * Validates that repeat steps do NOT reference from steps which will cause repeat steps overlap.
     * Although this might be possible (nested repeats), this version does not cover this scenario.
     */
    private static boolean validateRepeatOverlap(List<Mesg> messages) {
        List<Mesg> stepMessages = messages.subList(2, messages.size());
        
        for (int i = 0; i < stepMessages.size(); i++) {
            WorkoutStepMesg m = (WorkoutStepMesg) stepMessages.get(i);
            
            if (isRepeatStepMesg(m)) {
                long fromStep = m.getDurationValue();
                long repeatIndex = m.getMessageIndex();

                // checks if there is any repeat between current repeat step and fromStep
                for(long j = fromStep; j < repeatIndex; j++) {
                    m = (WorkoutStepMesg) stepMessages.get((int) j);
                    if (isRepeatStepMesg(m)) {
                        System.out.printf(INVALID_REPEAT_OVERLAP_TEMPLATE, repeatIndex, m.getMessageIndex());
                        return false;
                    }
                }
            }
        }       
       
        return true;
    } 

    private static boolean isRepeatStepMesg(WorkoutStepMesg m) {
        return (m.getDurationType() == WktStepDuration.REPEAT_UNTIL_STEPS_CMPLT);
    }
}