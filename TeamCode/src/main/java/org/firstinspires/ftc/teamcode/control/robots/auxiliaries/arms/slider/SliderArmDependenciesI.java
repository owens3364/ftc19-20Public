package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface SliderArmDependenciesI extends DependencySupplier, Refreshable {
    String[] getSliderDriveNames();
    DcMotorSimple.Direction[] getSliderDriveDirections();
    DcMotor[] getSliderDrives();
}
