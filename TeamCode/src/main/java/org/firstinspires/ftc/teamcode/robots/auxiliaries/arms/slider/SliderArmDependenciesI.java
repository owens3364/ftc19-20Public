package org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.slider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.robots.DependencySupplier;

public interface SliderArmDependenciesI extends DependencySupplier {
    String getSliderDriveName();
    DcMotorSimple.Direction getSliderDriveDirection();
    DcMotor getSliderDrive();
}
