package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SliderArm implements SliderArmI {

    private final DcMotor[] sliderDrives;

    public SliderArm(HardwareMap map) {
        SliderArmDependenciesI dependencies = new SliderArmDependencies();
        dependencies.resolveDependencies(map);
        sliderDrives = dependencies.getSliderDrives();
    }

    public SliderArm(HardwareMap map, SliderArmDependenciesI dependencies) {
        dependencies.resolveDependencies(map);
        sliderDrives = dependencies.getSliderDrives();
    }

    @Override
    public void setLiftPower(double power) {
        for (DcMotor sliderDrive : sliderDrives) {
            sliderDrive.setPower(power);
        }
    }

    @Override
    public double getLiftPosition() {
        return sliderDrives[0].getCurrentPosition();
    }

    @Override
    public void setLiftPosition(int ticks) {
        for (DcMotor sliderDrive : sliderDrives) {
            sliderDrive.setTargetPosition(ticks);
        }
    }

    @Override
    public DcMotor[] getLiftDrives() {
        return sliderDrives;
    }
}
