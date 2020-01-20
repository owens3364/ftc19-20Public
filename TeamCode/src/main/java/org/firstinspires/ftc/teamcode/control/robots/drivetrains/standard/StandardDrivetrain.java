package org.firstinspires.ftc.teamcode.control.robots.drivetrains.standard;

import org.firstinspires.ftc.teamcode.control.robots.WheelI;

public class StandardDrivetrain implements StandardDrivetrainI {

    private final WheelI[] leftWheels;
    private final WheelI[] rightWheels;

    public StandardDrivetrain(WheelI[] leftWheels, WheelI[] rightWheels) {
        this.leftWheels = leftWheels;
        this.rightWheels = rightWheels;
    }

    @Override
    public WheelI[] getLeft() {
        return leftWheels;
    }

    @Override
    public WheelI[] getRight() {
        return rightWheels;
    }
}
