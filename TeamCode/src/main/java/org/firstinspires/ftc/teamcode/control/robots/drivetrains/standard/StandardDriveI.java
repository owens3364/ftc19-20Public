package org.firstinspires.ftc.teamcode.control.robots.drivetrains.standard;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface StandardDriveI extends Refreshable {
    StandardDrivetrainI driveBySticks(StandardDriveInputI input);
    StandardDrivetrainI all(double power);
    StandardDrivetrainI only(double ... powers);
}
