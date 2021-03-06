package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface StatefulServoDependenciesI extends DependencySupplier, Refreshable {
    String getServoName();
    Servo.Direction getServoDirection();
    Servo getServo();
}
