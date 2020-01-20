package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos;

import com.qualcomm.robotcore.hardware.Servo;

public interface AuxiliaryArmsI {
    double getLeftServoPosition();
    void setLeftServoPosition(double position);

    void incrLeft(double incr);

    Servo getLeftServoA();

    double getRightServoPosition();
    void setRightServoPosition(double position);

    void incrRight(double incr);

    Servo getRightServoA();
}
