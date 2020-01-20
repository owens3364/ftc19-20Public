package org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum;

public interface MecanumDriveI {
    MecanumDrivetrainI driveBySticks(MecanumDriveInputI input);
    MecanumDrivetrainI all(double power);
    MecanumDrivetrainI only(double ... powers);
    MecanumDrivetrainI getDrivetrain();
}
