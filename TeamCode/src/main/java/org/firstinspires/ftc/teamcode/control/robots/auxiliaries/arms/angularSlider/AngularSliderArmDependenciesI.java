package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.angularSlider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider.SliderArmDependenciesI;

public interface AngularSliderArmDependenciesI extends SliderArmDependenciesI, Refreshable {
    String[] getAngularDriveNames();
    DcMotorSimple.Direction[] getAngularDriveDirections();
    DcMotor[] getAngularDrives();
}
