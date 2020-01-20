package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.intakes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.ObjectPair;

public class CompliantIntake implements CompliantIntakeI {
    private final ObjectPair<Servo, Servo> pitchServos;
    private final Servo leftServo;
    private final Servo rightServo;
    private final ObjectPair<DcMotor, DcMotor> intakeDrives;

    public CompliantIntake(HardwareMap map) {
        CompliantIntakeDependenciesI dependencies = new CompliantIntakeDependencies();
        dependencies.resolveDependencies(map);
        pitchServos = dependencies.getPitchServos();
        leftServo = dependencies.getLeftServo();
        rightServo = dependencies.getRightServo();
        intakeDrives = dependencies.getIntakeDrives();
    }

    public CompliantIntake(HardwareMap map, CompliantIntakeDependenciesI dependencies) {
        dependencies.resolveDependencies(map);
        pitchServos = dependencies.getPitchServos();
        leftServo = dependencies.getLeftServo();
        rightServo = dependencies.getRightServo();
        intakeDrives = dependencies.getIntakeDrives();
    }

    @Override
    public ObjectPair<Double, Double> getIntakePitchPositions() {
        return new ObjectPair<>(pitchServos.getA().getPosition(), pitchServos.getA().getPosition());
    }

    @Override
    public void setIntakePitchPositions(double position) {
        pitchServos.getA().setPosition(position);
        pitchServos.getB().setPosition(1 - position);
    }

    @Override
    public double getLeftPosition() {
        return leftServo.getPosition();
    }

    @Override
    public void setLeftPosition(double position) {
        leftServo.setPosition(position);
    }

    @Override
    public double getRightPosition() {
        return rightServo.getPosition();
    }

    @Override
    public void setRightPosition(double position) {
        rightServo.setPosition(position);
    }

    @Override
    public ObjectPair<Double, Double> getIntakeSpeeds() {
        return new ObjectPair<>(intakeDrives.getA().getPower(), intakeDrives.getB().getPower());
    }

    @Override
    public void setIntakeSpeeds(double speed) {
        intakeDrives.getA().setPower(speed);
        intakeDrives.getB().setPower(speed);
    }

    @Override
    public ObjectPair<Servo, Servo> getIntakePitch() {
        return pitchServos;
    }

    @Override
    public Servo getLeft() {
        return leftServo;
    }

    @Override
    public Servo getRight() {
        return rightServo;
    }

    @Override
    public ObjectPair<DcMotor, DcMotor> getIntake() {
        return intakeDrives;
    }
}
