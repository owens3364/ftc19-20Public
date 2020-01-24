package org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface MecanumDriveI extends Refreshable {
    MecanumDrivetrainI driveBySticks(MecanumDriveInputI input);
    MecanumDrivetrainI all(double power);
    MecanumDrivetrainI only(double ... powers);
    MecanumDrivetrainI getDrivetrain();
}
