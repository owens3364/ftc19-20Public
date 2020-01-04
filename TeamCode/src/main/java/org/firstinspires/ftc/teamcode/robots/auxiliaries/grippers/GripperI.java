package org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers;

import com.qualcomm.robotcore.hardware.Servo;

public interface GripperI {
    double getPitchPosition();
    void setPitchPosition(double position);
    double getIntakePosition();
    void setIntakePosition(double position);
    double getGripperPosition();
    void setGripperPosition(double position);

    Servo getPitch();
    Servo getIntake();
    Servo getGripper();
}
