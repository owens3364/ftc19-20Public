package org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum;

import org.firstinspires.ftc.teamcode.control.robots.WheelI;

public interface MecanumDrivetrainI {
    WheelI getFrontLeft();
    WheelI getFrontRight();
    WheelI getRearLeft();
    WheelI getRearRight();
}
