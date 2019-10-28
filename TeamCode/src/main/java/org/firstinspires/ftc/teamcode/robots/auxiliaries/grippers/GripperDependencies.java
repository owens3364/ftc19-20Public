package org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class GripperDependencies implements GripperDependenciesI {
    private static final String LEFT_SERVO_NAME = "LS";
    private static final String RIGHT_SERVO_NAME = "RS";

    private String leftServoName;
    private String rightServoName;

    private Servo leftServo;
    private Servo rightServo;

    private HardwareMap hardwareMap;

    GripperDependencies setLeftServoName(String name) {
        leftServoName = name;
        return this;
    }

    GripperDependencies setRightServoName(String name) {
        rightServoName = name;
        return this;
    }

    GripperDependencies setLeftServo(Servo servo) {
        leftServo = servo;
        return this;
    }

    GripperDependencies setRightServo(Servo servo) {
        rightServo = servo;
        return this;
    }


    @Override
    public String getLeftServoName() {
        return leftServoName;
    }

    @Override
    public Servo getLeftServo() {
        return leftServo;
    }

    @Override
    public String getRightServoName() {
        return rightServoName;
    }

    @Override
    public Servo getRightServo() {
        return rightServo;
    }

    @Override
    public HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    @Override
    public void resolveDependencies() {
        if (leftServo == null) {
            if (leftServoName != null) {
                leftServo = hardwareMap.servo.get(leftServoName);
            } else {
                leftServo = hardwareMap.servo.get(LEFT_SERVO_NAME);
                leftServoName = LEFT_SERVO_NAME;
            }
        }
        if (rightServo == null) {
            if (rightServoName != null) {
                rightServo = hardwareMap.servo.get(rightServoName);
            } else {
                rightServo = hardwareMap.servo.get(RIGHT_SERVO_NAME);
                rightServoName = RIGHT_SERVO_NAME;
            }
        }
    }

    @Override
    public void resolveDependencies(HardwareMap map) {
        hardwareMap = map;
        resolveDependencies();
    }
}
