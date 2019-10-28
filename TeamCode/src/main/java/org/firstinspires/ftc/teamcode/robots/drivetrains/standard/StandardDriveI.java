package org.firstinspires.ftc.teamcode.robots.drivetrains.standard;

public interface StandardDriveI {
    StandardDrivetrainI driveBySticks(StandardDriveInputI input);
    StandardDrivetrainI all(double power);
    StandardDrivetrainI only(double ... powers);
}
