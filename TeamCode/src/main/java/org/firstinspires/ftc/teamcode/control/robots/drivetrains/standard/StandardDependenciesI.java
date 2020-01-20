package org.firstinspires.ftc.teamcode.control.robots.drivetrains.standard;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;

public interface StandardDependenciesI extends DependencySupplier {
    String[] getLeftDriveNames();
    String[] getRightDriveNames();

    DcMotorSimple.Direction[] getLeftDriveDirections();
    DcMotorSimple.Direction[] getRightDriveDirections();

    DcMotor[] getLeftDrives();
    DcMotor[] getRightDrives();
}
