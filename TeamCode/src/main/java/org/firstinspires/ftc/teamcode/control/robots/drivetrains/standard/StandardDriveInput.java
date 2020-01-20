package org.firstinspires.ftc.teamcode.control.robots.drivetrains.standard;

public class StandardDriveInput implements StandardDriveInputI {
    private final boolean tankMode;
    private final Double left;
    private final Double right;
    private final Double y;
    private final Double turn;

    public StandardDriveInput(boolean tankMode, Double left, Double right, Double y, Double turn) {
        this.tankMode = tankMode;
        this.left = left;
        this.right = right;
        this.y = y;
        this.turn = turn;

        if (tankMode) {
            assert left != null;
            assert right != null;
        } else {
            assert y != null;
            assert turn != null;
        }
    }

    @Override
    public boolean isTankMode() {
        return tankMode;
    }

    @Override
    public double getLeft() {
        return left;
    }

    @Override
    public double getRight() {
        return right;
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
