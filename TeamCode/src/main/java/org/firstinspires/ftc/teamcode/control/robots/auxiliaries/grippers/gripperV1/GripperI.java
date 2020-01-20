package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.grippers.gripperV1;

import com.qualcomm.robotcore.hardware.Servo;

public interface GripperI {
    double getPitchPosition();
    void setPitchPosition(double position);
    double getYawPosition();
    void setYawPosition(double position);
    double getGripperPosition();
    void setGripperPosition(double position);

    Servo getPitch();
    Servo getYaw();
    Servo getGripper();
}
