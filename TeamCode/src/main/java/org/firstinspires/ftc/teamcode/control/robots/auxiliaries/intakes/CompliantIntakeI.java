package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.intakes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.ObjectPair;

public interface CompliantIntakeI {
    ObjectPair<Double, Double> getIntakePitchPositions();
    void setIntakePitchPositions(double position);
    double getLeftPosition();
    void setLeftPosition(double position);
    double getRightPosition();
    void setRightPosition(double position);
    ObjectPair<Double, Double> getIntakeSpeeds();
    void setIntakeSpeeds(double speed);

    ObjectPair<Servo, Servo> getIntakePitch();
    Servo getLeft();
    Servo getRight();
    ObjectPair<DcMotor, DcMotor> getIntake();
}
