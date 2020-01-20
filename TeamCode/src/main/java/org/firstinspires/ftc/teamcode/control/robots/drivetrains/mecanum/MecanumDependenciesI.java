package org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;

public interface MecanumDependenciesI extends DependencySupplier {
    String getFrontLeftDriveName();
    String getFrontRightDriveName();
    String getRearLeftDriveName();
    String getRearRightDriveName();

    DcMotorSimple.Direction getFrontLeftDriveDirection();
    DcMotorSimple.Direction getFrontRightDriveDirection();
    DcMotorSimple.Direction getRearLeftDriveDirection();
    DcMotorSimple.Direction getRearRightDriveDirection();

    DcMotor getFrontLeftDrive();
    DcMotor getFrontRightDrive();
    DcMotor getRearLeftDrive();
    DcMotor getRearRightDrive();
}
