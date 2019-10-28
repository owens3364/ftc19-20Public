package org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.angularSlider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.slider.SliderArm;

public class AngularSliderArm extends SliderArm implements AngularSliderArmI {

    private final DcMotor angularDrive;

    public AngularSliderArm(HardwareMap map) {
        super(map);
        AngularSliderArmDependenciesI dependencies = new AngularSliderArmDependencies();
        dependencies.resolveDependencies(map);
        angularDrive = dependencies.getAngularDrive();
    }

    public AngularSliderArm(HardwareMap map, AngularSliderArmDependenciesI dependencies) {
        super(map, dependencies);
        dependencies.resolveDependencies(map);
        angularDrive = dependencies.getAngularDrive();
    }

    @Override
    public void setAngularPower(double power) {
        angularDrive.setPower(power);
    }

    @Override
    public double getAngularPosition() {
        return angularDrive.getCurrentPosition();
    }

    @Override
    public void setAngularPosition(int ticks) {
        angularDrive.setTargetPosition(ticks);
    }

    @Override
    public DcMotor getAngular() {
        return angularDrive;
    }
}
