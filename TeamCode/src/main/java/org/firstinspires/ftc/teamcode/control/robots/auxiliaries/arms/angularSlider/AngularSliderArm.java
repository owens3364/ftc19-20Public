package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.angularSlider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider.SliderArm;

public class AngularSliderArm extends SliderArm implements AngularSliderArmI {
    private final AngularSliderArmDependenciesI dependencies;
    private final DcMotor[] angularDrives;

    public AngularSliderArm(HardwareMap map) {
        super(map);
        dependencies = new AngularSliderArmDependencies();
        dependencies.resolveDependencies(map);
        angularDrives = dependencies.getAngularDrives();
    }

    public AngularSliderArm(HardwareMap map, AngularSliderArmDependenciesI dependencies) {
        super(map, dependencies);
        this.dependencies = dependencies;
        dependencies.resolveDependencies(map);
        angularDrives = dependencies.getAngularDrives();
    }

    @Override
    public void setAngularPower(double power) {
        for (DcMotor angularDrive : angularDrives) {
            angularDrive.setPower(power);
        }
    }

    @Override
    public double getAngularPosition() {
        return angularDrives[0].getCurrentPosition();
    }

    @Override
    public void setAngularPosition(int ticks) {
        for (DcMotor angularDrive : angularDrives) {
            angularDrive.setTargetPosition(ticks);
        }
    }

    @Override
    public DcMotor[] getAngularDrives() {
        return angularDrives;
    }

    @Override
    public Refreshable refresh() {
        dependencies.refresh();
        return null;
    }
}
