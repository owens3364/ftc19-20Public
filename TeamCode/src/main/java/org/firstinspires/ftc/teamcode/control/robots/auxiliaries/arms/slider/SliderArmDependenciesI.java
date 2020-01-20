package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;

public interface SliderArmDependenciesI extends DependencySupplier {
    String[] getSliderDriveNames();
    DcMotorSimple.Direction[] getSliderDriveDirections();
    DcMotor[] getSliderDrives();
}
