package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.intakes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;
import org.firstinspires.ftc.teamcode.utils.ObjectPair;

public interface CompliantIntakeDependenciesI extends DependencySupplier, Refreshable {
    ObjectPair<String, String> getPitchServoNames();
    ObjectPair<Servo, Servo> getPitchServos();
    String getLeftServoName();
    Servo getLeftServo();
    String getRightServoName();
    Servo getRightServo();
    ObjectPair<String, String> getIntakeDriveNames();
    ObjectPair<DcMotorSimple.Direction, DcMotorSimple.Direction> getIntakeDriveDirections();
    ObjectPair<DcMotor, DcMotor> getIntakeDrives();
}
