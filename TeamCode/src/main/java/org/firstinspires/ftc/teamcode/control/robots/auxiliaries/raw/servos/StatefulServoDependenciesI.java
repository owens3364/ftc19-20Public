package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;

public interface StatefulServoDependenciesI extends DependencySupplier {
    String getServoName();
    Servo.Direction getServoDirection();
    Servo getServo();
}
