package org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robots.DependencySupplier;

public interface GripperDependenciesI extends DependencySupplier {
    String getLeftServoName();
    Servo getLeftServo();
    String getRightServoName();
    Servo getRightServo();
}
