package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface StatefulServoI extends Refreshable {
    double getPosition();
    void setPosition(double position);

    void incr(double incr);

    Servo getServo();
}
