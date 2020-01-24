package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface AuxiliaryArmsI extends Refreshable {
    double getLeftServoPosition();
    void setLeftServoPosition(double position);

    void incrLeft(double incr);

    Servo getLeftServoA();

    double getRightServoPosition();
    void setRightServoPosition(double position);

    void incrRight(double incr);

    Servo getRightServoA();

    @Override
    default Refreshable refresh() {
        return this;
    }
}
