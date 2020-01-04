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
    public DcMotor[] getAngularDrives() {
        return angularSliderArm.getAngularDrives();
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
    public DcMotor[] getLiftDrives() {
        return angularSliderArm.getLiftDrives();
    }

    @Override
    public double getPitchPosition() {
        return gripper.getPitchPosition();
    }

    @Override
    public void setPitchPosition(double position) {
        gripper.setPitchPosition(position);
    }

    @Override
    public double getIntakePosition() {
        return gripper.getIntakePosition();
    }

    @Override
    public void setIntakePosition(double position) {
        gripper.setIntakePosition(position);
    }

    @Override
    public double getGripperPosition() {
        return gripper.getGripperPosition();
    }

    @Override
    public void setGripperPosition(double position) {
        gripper.setGripperPosition(position);
    }

    @Override
    public Servo getPitch() {
        return gripper.getPitch();
    }

    @Override
    public Servo getIntake() {
        return gripper.getIntake();
    }

    @Override
    public Servo getGripper() {
        return gripper.getGripper();
    }
}
