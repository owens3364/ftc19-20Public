package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class StatefulServoDependencies implements StatefulServoDependenciesI {

    private String servoName;

    private Servo.Direction servoDirection;

    private Servo servo;

    private HardwareMap hardwareMap;

    public StatefulServoDependencies setServoName(String name) {
        servoName = name;
        return this;
    }

    public StatefulServoDependencies setServoDirection(Servo.Direction direction) {
        servoDirection = direction;
        return this;
    }

    public StatefulServoDependencies setServo(Servo servo) {
        this.servo = servo;
        return this;
    }

    @Override
    public String getServoName() {
        return servoName;
    }

    @Override
    public Servo.Direction getServoDirection() {
        return servoDirection;
    }

    @Override
    public Servo getServo() {
        return servo;
    }

    @Override
    public HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    @Override
    public void resolveDependencies() {
        if (servo == null) {
            servo = hardwareMap.servo.get(servoName);
        }
    }

    @Override
    public void resolveDependencies(HardwareMap map) {
        hardwareMap = map;
        resolveDependencies();
    }

    @Override
    public Refreshable refresh() {
        return this;
    }
}
