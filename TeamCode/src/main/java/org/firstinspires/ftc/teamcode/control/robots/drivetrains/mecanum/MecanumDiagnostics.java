package org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum;

public interface MecanumDiagnostics {
    double getFrontLeftDrivePower();
    double getFrontRightDrivePower();
    double getRearLeftDrivePower();
    double getRearRightDriverPower();
    int getFrontLeftDrivePosition();
    int getFrontRightDrivePosition();
    int getRearLeftDrivePosition();
    int getRearRightDrivePosition();
    String getFrontLeftDriveMode();
    String getFrontRightDriveMode();
    String getRearLeftDriveMode();
    String getRearRightDriveMode();
    String getFrontLeftDriveDirection();
    String getFrontRightDriveDirection();
    String getRearLeftDriveDirection();
    String getRearRightDriveDirection();
}
