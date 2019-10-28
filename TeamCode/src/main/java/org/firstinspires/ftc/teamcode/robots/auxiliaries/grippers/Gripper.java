package org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Gripper implements GripperI {
    private final Servo leftServo;
    private final Servo rightServo;

    public Gripper(HardwareMap map) {
        GripperDependenciesI dependencies = new GripperDependencies();
        dependencies.resolveDependencies(map);
        leftServo = dependencies.getLeftServo();
        rightServo = dependencies.getRightServo();
    }

    public Gripper(HardwareMap map, GripperDependenciesI dependencies) {
        dependencies.resolveDependencies(map);
        leftServo = dependencies.getLeftServo();
        rightServo = dependencies.getRightServo();
    }


    @Override
    public double getLeftPosition() {
        return leftServo.getPosition();
    }

    @Override
    public void setLeftPosition(double position) {
        leftServo.setPosition(position);
    }

    @Override
    public double getRightPosition() {
        return rightServo.getPosition();
    }

    @Override
    public void setRightPosition(double position) {
        rightServo.setPosition(position);
    }

    @Override
    public Servo getLeft() {
        return leftServo;
    }

    @Override
    public Servo getRight() {
        return rightServo;
    }
}
