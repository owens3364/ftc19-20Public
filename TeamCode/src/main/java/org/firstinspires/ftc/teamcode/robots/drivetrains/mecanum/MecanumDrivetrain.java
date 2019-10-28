package org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum;

import org.firstinspires.ftc.teamcode.robots.WheelI;

public class MecanumDrivetrain implements MecanumDrivetrainI{
    private final WheelI frontLeft;
    private final WheelI frontRight;
    private final WheelI rearLeft;
    private final WheelI rearRight;

    public MecanumDrivetrain(WheelI frontLeft, WheelI frontRight, WheelI rearLeft, WheelI rearRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
    }

    @Override
    public WheelI getFrontLeft() {
        return frontLeft;
    }

    @Override
    public WheelI getFrontRight() {
        return frontRight;
    }

    @Override
    public WheelI getRearLeft() {
        return rearLeft;
    }

    @Override
    public WheelI getRearRight() {
        return rearRight;
    }
}
