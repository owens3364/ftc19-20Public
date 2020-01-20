package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider;

public interface SliderArmDiagnostics {
    double getLiftDriveAPower();
    double getLiftDriveBPower();
    int getLiftDriveAPosition();
    int getLiftDriveBPosition();
    String getLiftDriveAMode();
    String getLiftDriveBMode();
    String getLiftDriveADirection();
    String getLiftDriveBDirection();
}
