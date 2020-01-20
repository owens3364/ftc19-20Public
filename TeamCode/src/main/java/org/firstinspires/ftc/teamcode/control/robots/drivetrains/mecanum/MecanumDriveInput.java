package org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum;

public class MecanumDriveInput implements MecanumDriveInputI{
    private final double x;
    private final double y;
    private final double turn;

    public MecanumDriveInput(double x, double y, double turn) {
        this.x = x;
        this.y = y;
        this.turn = turn;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getTurn() {
        return turn;
    }
}
