package org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.slider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SliderArm implements SliderArmI {

    private final DcMotor sliderDrive;

    public SliderArm(HardwareMap map) {
        SliderArmDependenciesI dependencies = new SliderArmDependencies();
        dependencies.resolveDependencies(map);
        sliderDrive = dependencies.getSliderDrive();
    }

    public SliderArm(HardwareMap map, SliderArmDependenciesI dependencies) {
        dependencies.resolveDependencies(map);
        sliderDrive = dependencies.getSliderDrive();
    }

    @Override
    public void setLiftPower(double power) {
        sliderDrive.setPower(power);
    }

    @Override
    public double getLiftPosition() {
        return sliderDrive.getCurrentPosition();
    }

    @Override
    public void setLiftPosition(int ticks) {
        sliderDrive.setTargetPosition(ticks);
    }

    @Override
    public DcMotor getLift() {
        return sliderDrive;
    }
}
