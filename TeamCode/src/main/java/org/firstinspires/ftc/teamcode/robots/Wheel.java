package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Wheel implements WheelI {
    private final WheelPosition position;
    private final DcMotor motor;

    public Wheel(WheelPosition position, DcMotor motor) {
        this.position = position;
        this.motor = motor;
    }

    @Override
    public WheelPosition getPosition() {
        return position;
    }

    @Override
    public DcMotor getMotor() {
        return motor;
    }

    public static WheelI[] fromArr(DcMotor[] motors, WheelPosition[] positions) {
        WheelI[] arr = new WheelI[Math.min(motors.length, positions.length)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Wheel(positions[i], motors[i]);
        }
        return arr;
    }
}