package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.grippers.gripperV1;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;

public interface GripperDependenciesI extends DependencySupplier {
    String getPitchServoName();
    Servo getPitchServo();
    String getYawServoName();
    Servo getYawServo();
    String getGripperServoName();
    Servo getGripperServo();
}
