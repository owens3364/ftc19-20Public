package org.firstinspires.ftc.teamcode.robots.auxiliaries.raw.servos;

import com.qualcomm.robotcore.hardware.Servo;

public interface StatefulServoI {
    double getPosition();
    void setPosition(double position);

    void incr(double incr);

    Servo getServo();
}
