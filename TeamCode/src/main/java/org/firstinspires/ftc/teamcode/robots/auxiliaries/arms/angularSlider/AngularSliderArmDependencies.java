package org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.angularSlider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.slider.SliderArmDependencies;

public class AngularSliderArmDependencies extends SliderArmDependencies implements AngularSliderArmDependenciesI {
    private static final String ANGULAR_DRIVE_NAME = "A0";

    private static final DcMotorSimple.Direction ANGULAR_DRIVE_DIRECTION = DcMotorSimple.Direction.FORWARD;

    private String angularDriveName;

    private DcMotorSimple.Direction angularDriveDirection;

    private DcMotor angularDrive;

    private HardwareMap hardwareMap;

    AngularSliderArmDependencies setSliderDriveName(String name) {
        angularDriveName = name;
        return this;
    }

    AngularSliderArmDependencies setSliderDriveDirection(DcMotorSimple.Direction direction) {
        angularDriveDirection = direction;
        return this;
    }

    AngularSliderArmDependencies setSliderDrive(DcMotor motor) {
        angularDrive = motor;
        return this;
    }

    @Override
    public String getAngularDriveName() {
        return angularDriveName;
    }

    @Override
    public DcMotorSimple.Direction getAngularDriveDirection() {
        return angularDriveDirection;
    }

    @Override
    public DcMotor getAngularDrive() {
        return angularDrive;
    }

    @Override
    public HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    @Override
    public void resolveDependencies() {
        super.resolveDependencies();
        if (angularDrive == null) {
            if (angularDriveName != null) {
                angularDrive = hardwareMap.dcMotor.get(angularDriveName);
            } else {
                angularDrive = hardwareMap.dcMotor.get(ANGULAR_DRIVE_NAME);
                angularDriveName = ANGULAR_DRIVE_NAME;
            }
        }
        if (angularDriveDirection == null) {
            angularDriveDirection = ANGULAR_DRIVE_DIRECTION;
        }
        angularDrive.setDirection(angularDriveDirection);
    }

    @Override
    public void resolveDependencies(HardwareMap map) {
        super.resolveDependencies(map);
        hardwareMap = map;
        resolveDependencies();
    }
}
