package org.firstinspires.ftc.teamcode.control.robots.drivetrains.standard;

public interface StandardDriveI {
    StandardDrivetrainI driveBySticks(StandardDriveInputI input);
    StandardDrivetrainI all(double power);
    StandardDrivetrainI only(double ... powers);
}
