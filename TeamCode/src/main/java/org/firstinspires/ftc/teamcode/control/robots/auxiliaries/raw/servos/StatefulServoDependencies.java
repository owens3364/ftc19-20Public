package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class StatefulServoDependencies implements StatefulServoDependenciesI {
    private static final byte DEFAULT_PRIORITY = 0xA;

    private String servoName;

    private Servo.Direction servoDirection;

    private Servo servo;

    private HardwareMap hardwareMap;

    private byte priority = -1;

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

    public StatefulServoDependencies setPriority(byte priority) {
        this.priority = priority;
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
    public DependencySupplier setHardwareMap(HardwareMap map) {
        hardwareMap = map;
        return this;
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
    public int getPriority() {
        if (priority == -1) priority = DEFAULT_PRIORITY;
        return priority;
    }

    @Override
    public Refreshable refresh() {
        return this;
    }
}
