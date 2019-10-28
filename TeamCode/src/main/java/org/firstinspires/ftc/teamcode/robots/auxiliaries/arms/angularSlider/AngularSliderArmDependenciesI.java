package org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.angularSlider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.slider.SliderArmDependenciesI;

public interface AngularSliderArmDependenciesI extends SliderArmDependenciesI {
    String getAngularDriveName();
    DcMotorSimple.Direction getAngularDriveDirection();
    DcMotor getAngularDrive();
}
