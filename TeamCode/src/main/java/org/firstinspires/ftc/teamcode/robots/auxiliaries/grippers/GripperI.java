package org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers;

import com.qualcomm.robotcore.hardware.Servo;

public interface GripperI {
    double getLeftPosition();
    void setLeftPosition(double position);
    double getRightPosition();
    void setRightPosition(double position);

    Servo getLeft();
    Servo getRight();
}
