package org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum;

import org.firstinspires.ftc.teamcode.robots.WheelI;

public interface MecanumDrivetrainI {
    WheelI getFrontLeft();
    WheelI getFrontRight();
    WheelI getRearLeft();
    WheelI getRearRight();
}
