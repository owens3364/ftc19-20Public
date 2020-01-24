package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.grippers.gripperV1;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class Gripper implements GripperI {
    private final Servo pitchServo;
    private final Servo yawServo;
    private final Servo gripperServo;
    private final GripperDependenciesI dependencies;

    public Gripper(HardwareMap map) {
        dependencies = new GripperDependencies();
        dependencies.resolveDependencies(map);
        pitchServo = dependencies.getPitchServo();
        yawServo = dependencies.getYawServo();
        gripperServo = dependencies.getGripperServo();
    }

    public Gripper(HardwareMap map, GripperDependenciesI dependencies) {
        this.dependencies = dependencies;
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
    public double getYawPosition() {
        return yawServo.getPosition();
    }

    @Override
    public void setYawPosition(double position) {
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
    public Servo getYaw() {
        return yawServo;
    }

    @Override
    public Servo getGripper() {
        return gripperServo;
    }

    @Override
    public Refreshable refresh() {
        dependencies.refresh();
        return this;
    }
}
