package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.angularSlider.AngularSliderArm;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.angularSlider.AngularSliderArmDependenciesI;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.angularSlider.AngularSliderArmI;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers.Gripper;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers.GripperDependenciesI;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers.GripperI;
import org.firstinspires.ftc.teamcode.robots.drivetrains.standard.StandardDependenciesI;
import org.firstinspires.ftc.teamcode.robots.drivetrains.standard.StandardDrive;
import org.firstinspires.ftc.teamcode.robots.drivetrains.standard.StandardDriveI;

public class ArmfulOutreachRobot extends ArmlessOutreachRobot implements AngularSliderArmI, GripperI {
    private final AngularSliderArmI angularSliderArm;
    private final GripperI gripper;

    public static ArmfulOutreachRobot instantiate(HardwareMap map) {
        return new ArmfulOutreachRobot(new StandardDrive(map), new AngularSliderArm(map), new Gripper(map));
    }

    public static ArmfulOutreachRobot instantiateWithDependencies(HardwareMap map, StandardDependenciesI standardDependencies, AngularSliderArmDependenciesI angularSliderArmDependencies, GripperDependenciesI gripperDependencies) {
        return new ArmfulOutreachRobot(new StandardDrive(map, standardDependencies), new AngularSliderArm(map, angularSliderArmDependencies), new Gripper(map, gripperDependencies));
    }

    private ArmfulOutreachRobot(StandardDriveI standardDrive, AngularSliderArmI angularSliderArm, GripperI gripper) {
        super(standardDrive);
        this.angularSliderArm = angularSliderArm;
        this.gripper = gripper;
    }

    @Override
    public void setAngularPower(double power) {
        angularSliderArm.setAngularPower(power);
    }

    @Override
    public double getAngularPosition() {
        return angularSliderArm.getAngularPosition();
    }

    @Override
    public void setAngularPosition(int ticks) {
        angularSliderArm.setAngularPosition(ticks);
    }

    @Override
    public DcMotor getAngular() {
        return angularSliderArm.getAngular();
    }

    @Override
    public void setLiftPower(double power) {
        angularSliderArm.setLiftPower(power);
    }

    @Override
    public double getLiftPosition() {
        return angularSliderArm.getLiftPosition();
    }

    @Override
    public void setLiftPosition(int ticks) {
        angularSliderArm.setLiftPosition(ticks);
    }

    @Override
    public DcMotor getLift() {
        return angularSliderArm.getLift();
    }

    @Override
    public double getLeftPosition() {
        return gripper.getLeftPosition();
    }

    @Override
    public void setLeftPosition(double position) {
        gripper.setLeftPosition(position);
    }

    @Override
    public double getRightPosition() {
        return gripper.getRightPosition();
    }

    @Override
    public void setRightPosition(double position) {
        gripper.setRightPosition(position);
    }

    @Override
    public Servo getLeft() {
        return gripper.getLeft();
    }

    @Override
    public Servo getRight() {
        return gripper.getRight();
    }
}
