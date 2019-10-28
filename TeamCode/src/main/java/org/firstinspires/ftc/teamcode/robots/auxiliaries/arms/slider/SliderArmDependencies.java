package org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.slider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SliderArmDependencies implements SliderArmDependenciesI {
    private static final String SLIDER_DRIVE_NAME = "S0";

    private static final DcMotorSimple.Direction SLIDER_DRIVE_DIRECTION = DcMotorSimple.Direction.FORWARD;

    private String sliderDriveName;

    private DcMotorSimple.Direction sliderDriveDirection;

    private DcMotor sliderDrive;

    private HardwareMap hardwareMap;

    SliderArmDependencies setSliderDriveName(String name) {
        sliderDriveName = name;
        return this;
    }

    SliderArmDependencies setSliderDriveDirection(DcMotorSimple.Direction direction) {
        sliderDriveDirection = direction;
        return this;
    }

    SliderArmDependencies setSliderDrive(DcMotor motor) {
        sliderDrive = motor;
        return this;
    }

    @Override
    public String getSliderDriveName() {
        return sliderDriveName;
    }

    @Override
    public DcMotorSimple.Direction getSliderDriveDirection() {
        return sliderDriveDirection;
    }

    @Override
    public DcMotor getSliderDrive() {
        return sliderDrive;
    }

    @Override
    public HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    @Override
    public void resolveDependencies() {
        if (sliderDrive == null) {
            if (sliderDriveName != null) {
                sliderDrive = hardwareMap.dcMotor.get(sliderDriveName);
            } else {
                sliderDrive = hardwareMap.dcMotor.get(SLIDER_DRIVE_NAME);
                sliderDriveName = SLIDER_DRIVE_NAME;
            }
        }
        if (sliderDriveDirection == null) {
            sliderDriveDirection = SLIDER_DRIVE_DIRECTION;
        }
        sliderDrive.setDirection(sliderDriveDirection);
    }

    @Override
    public void resolveDependencies(HardwareMap map) {
        hardwareMap = map;
        resolveDependencies();
    }
}
