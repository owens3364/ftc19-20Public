package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.grippers.gripperV2;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class GripperDependencies implements GripperDependenciesI {
    private static final String PITCH_SERVO_NAME = "PITCH";
    private static final String GRIPPER_SERVO_NAME = "GRIPPER";

    private String pitchServoName;
    private String gripperServoName;

    private Servo pitchServo;
    private Servo gripperServo;

    private HardwareMap hardwareMap;

    public GripperDependencies setPitchServoName(String name) {
        pitchServoName = name;
        return this;
    }

    public GripperDependencies setGripperServoName(String name) {
        gripperServoName = name;
        return this;
    }

    public GripperDependencies setPitchServo(Servo servo) {
        pitchServo = servo;
        return this;
    }

    public GripperDependencies setGripperServo(Servo servo) {
        gripperServo = servo;
        return this;
    }

    @Override
    public String getPitchServoName() {
        return pitchServoName;
    }

    @Override
    public Servo getPitchServo() {
        return pitchServo;
    }

    @Override
    public String getGripperServoName() {
        return gripperServoName;
    }

    @Override
    public Servo getGripperServo() {
        return gripperServo;
    }

    @Override
    public HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    @Override
    public void resolveDependencies() {
        if (pitchServo == null) {
            if (pitchServoName != null) {
                pitchServo = hardwareMap.servo.get(pitchServoName);
            } else {
                pitchServo = hardwareMap.servo.get(PITCH_SERVO_NAME);
                pitchServoName = PITCH_SERVO_NAME;
            }
        }
        if (gripperServo == null) {
            if (gripperServoName != null) {
                gripperServo = hardwareMap.servo.get(gripperServoName);
            } else {
                gripperServo = hardwareMap.servo.get(GRIPPER_SERVO_NAME);
                gripperServoName = GRIPPER_SERVO_NAME;
            }
        }
    }

    @Override
    public void resolveDependencies(HardwareMap map) {
        hardwareMap = map;
        resolveDependencies();
    }
}
