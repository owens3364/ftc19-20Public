package org.firstinspires.ftc.teamcode.robots;

public enum WheelPosition {
    FRONT_LEFT,
    FRONT_RIGHT,
    REAR_LEFT,
    REAR_RIGHT,
    LEFT,
    RIGHT;

    public static WheelPosition[] arrOf(WheelPosition pos, int length) {
        WheelPosition[] arr = new WheelPosition[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = pos;
        }
        return arr;
    }
}
