package org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Gripper implements GripperI {
    private final Servo pitchServo;
    private final Servo yawServo;
    private final Servo gripperServo;

    public Gripper(HardwareMap map) {
        GripperDependenciesI dependencies = new GripperDependencies();
        dependencies.resolveDependencies(map);
        pitchServo = dependencies.getPitchServo();
        yawServo = dependencies.getYawServo();
        gripperServo = dependencies.getGripperServo();
    }

    public Gripper(HardwareMap map, GripperDependenciesI dependencies) {
        dependencies.resolveDependencies(map);
        pitchServo = dependencies.getPitchServo();
        yawServo = dependencies.getYawServo();
        gripperServo = dependencies.getGripperServo();
    }


    @Override
    public double getPitchPosition() {
        return pitchServo.getPosition();
    }

    @Override
    public void setPitchPosition(double position) {
        pitchServo.setPosition(position);
    }

    @Override
    public double getIntakePosition() {
        return yawServo.getPosition();
    }

    @Override
    public void setIntakePosition(double position) {
        yawServo.setPosition(position);
    }

    @Override
    public double getGripperPosition() {
        return gripperServo.getPosition();
    }

    @Override
    public void setGripperPosition(double position) {
        gripperServo.setPosition(position);
    }

    @Override
    public Servo getPitch() {
        return pitchServo;
    }

    @Override
    public Servo getIntake() {
        return yawServo;
    }

    @Override
    public Servo getGripper() {
        return gripperServo;
    }
}
