package fit.mvp;

import com.garmin.fit.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class EncodeWorkout {
    public static void main(String[] args) {
        System.out.printf("FIT Encode Example Application - Protocol %d.%d Profile %.2f %s\n",
                           Fit.PROTOCOL_VERSION_MAJOR,
                           Fit.PROTOCOL_VERSION_MINOR,
                           Fit.PROFILE_VERSION / 100.0,
                           Fit.PROFILE_TYPE);

        new EncodeWorkout().encodeWorkout();
    }

    private void encodeWorkout() {
        System.out.println("Encode Workout");

        FileEncoder encode;
        String filePath = "Test_workout.fit";

        try {
            encode = new FileEncoder(new java.io.File(filePath), Fit.ProtocolVersion.V1_0);
        } catch (FitRuntimeException e) {
            System.err.printf("Error opening file %s.\n", filePath);
            return;
        }

        writeFileIdMesg(encode);
        writeWorkoutMesg(encode);
        writeWorkoutStepMesg(encode);

        // byte[] appId = new byte[]{
        //     0x1, 0x1, 0x2, 0x3,
        //     0x5, 0x8, 0xD, 0x15,
        //     0x22, 0x37, 0x59, (byte) 0x90,
        //     (byte) 0xE9, 0x79, 0x62, (byte) 0xDB
        // };

        // DeveloperDataIdMesg developerIdMesg = new DeveloperDataIdMesg();
        // for (int i = 0; i < appId.length; i++) {
        //     developerIdMesg.setApplicationId(i, appId[i]);
        // }
        // developerIdMesg.setDeveloperDataIndex((short)0);
        // encode.write(developerIdMesg);

        // FieldDescriptionMesg fieldDescMesg = new FieldDescriptionMesg();
        // fieldDescMesg.setDeveloperDataIndex((short)0);
        // fieldDescMesg.setFieldDefinitionNumber((short)0);
        // fieldDescMesg.setFitBaseTypeId((short) Fit.BASE_TYPE_SINT8);
        // fieldDescMesg.setFieldName(0, "doughnuts_earned");
        // fieldDescMesg.setUnits(0, "doughnuts");
        // encode.write(fieldDescMesg);

        // FieldDescriptionMesg hrFieldDescMesg = new FieldDescriptionMesg();
        // hrFieldDescMesg.setDeveloperDataIndex((short)0);
        // hrFieldDescMesg.setFieldDefinitionNumber((short)1);
        // hrFieldDescMesg.setFitBaseTypeId((short)Fit.BASE_TYPE_UINT8);
        // hrFieldDescMesg.setFieldName(0, "hr");
        // hrFieldDescMesg.setUnits(0, "bpm");
        // hrFieldDescMesg.setNativeFieldNum((short) RecordMesg.HeartRateFieldNum);
        // encode.write(hrFieldDescMesg);

        // RecordMesg record = new RecordMesg();
        // DeveloperField doughnutsEarnedField = new DeveloperField(fieldDescMesg, developerIdMesg);
        // DeveloperField hrDevField = new DeveloperField(hrFieldDescMesg, developerIdMesg);
        // record.addDeveloperField(doughnutsEarnedField);
        // record.addDeveloperField(hrDevField);

        // record.setHeartRate((short)140);
        // hrDevField.setValue((short)140);
        // record.setCadence((short)88);
        // record.setDistance(510f);
        // record.setSpeed(2800f);
        // doughnutsEarnedField.setValue(1);
        // encode.write(record);

        // record.setHeartRate(Fit.UINT8_INVALID );
        // hrDevField.setValue((short)143);
        // record.setCadence((short)90);
        // record.setDistance(2080f);
        // record.setSpeed(2920f);
        // doughnutsEarnedField.setValue(2);
        // encode.write(record);

        // record.setHeartRate((short)144);
        // hrDevField.setValue((short)144);
        // record.setCadence((short)92);
        // record.setDistance(3710f);
        // record.setSpeed(3050f);
        // doughnutsEarnedField.setValue(3);
        // encode.write(record);

        try {
            encode.close();
        } catch (FitRuntimeException e) {
            System.err.println("Error closing encode.");
            return;
        }

        System.out.printf("Encoded FIT file %s.\n", filePath);
    }

    private void writeFileIdMesg(FileEncoder encode) {
        FileIdMesg fileIdMesg = new FileIdMesg(); // Every FIT file MUST contain a 'File ID' message as the first message
        Date now = new Date();
        fileIdMesg.setType(File.WORKOUT);
        fileIdMesg.setManufacturer(Manufacturer.GARMIN);
        fileIdMesg.setProduct(GarminProduct.EDGE500);
        fileIdMesg.setSerialNumber(generateSerialNumber(now));
        fileIdMesg.setTimeCreated(new DateTime(now));

        encode.write(fileIdMesg);
    }

    private void writeWorkoutMesg(FileEncoder encode) {
        WorkoutMesg wktMsg = new WorkoutMesg();
        wktMsg.setSport(Sport.CYCLING);
        wktMsg.setNumValidSteps(1);
        wktMsg.setWktName("Test");
        encode.write(wktMsg);
    }

    private void writeWorkoutStepMesg(FileEncoder encode) {
        WorkoutStepMesg wktStepMsg = new WorkoutStepMesg();
        wktStepMsg.setMessageIndex(0);
        wktStepMsg.setWktStepName("Step 1");
        wktStepMsg.setDurationType(WktStepDuration.OPEN);
        wktStepMsg.setTargetType(WktStepTarget.OPEN);
        encode.write(wktStepMsg);
    }

    private long generateSerialNumber(Date date) {
        return date.getTime() / 1000;
    }
}

