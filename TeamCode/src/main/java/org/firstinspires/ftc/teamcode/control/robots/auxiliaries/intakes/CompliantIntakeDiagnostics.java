package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.intakes;

public interface CompliantIntakeDiagnostics {
    double getIntakeDriveAPower();
    double getIntakeDriveBPower();
    int getIntakeDriveAPosition();
    int getIntakeDriveBPosition();
    String getIntakeDriveAMode();
    String getIntakeDriveBMode();
    String getIntakeDriveADirection();
    String getIntakeDriveBDirection();

    double getIntakePitchAPosition();
    double getIntakePitchBPosition();
    String getIntakePitchADirection();
    String getIntakePitchBDirection();
    String getIntakeLeftDirection();
    String getIntakeRightDirection();
}
