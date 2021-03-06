package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class StatefulServo implements StatefulServoI {
    private final Servo servo;

    private double servoTarget = 0.0;

    private final StatefulServoDependenciesI dependencies;

    public StatefulServo(HardwareMap map) {
        dependencies = new StatefulServoDependencies();
        dependencies.resolveDependencies(map);
        servo = dependencies.getServo();
    }

    public StatefulServo(HardwareMap map, StatefulServoDependenciesI dependencies) {
        this.dependencies = dependencies;
        dependencies.resolveDependencies(map);
        servo = dependencies.getServo();
    }

    @Override
    public double getPosition() {
        return servo.getPosition();
    }

    @Override
    public void setPosition(double position) {
        servoTarget = position;
        servoTarget = servoTarget < -1.0 ? -1.0 : servoTarget > 1.0 ? 1.0 : servoTarget;
        servo.setPosition(Range.scale(servoTarget, -1, 1, 0, 1));
    }

    @Override
    public void incr(double incr) {
        servoTarget += incr;
        servoTarget = servoTarget < -1.0 ? -1.0 : servoTarget > 1.0 ? 1.0 : servoTarget;
        servo.setPosition(Range.scale(servoTarget, -1, 1, 0, 1));
    }

    @Override
    public Servo getServo() {
        return servo;
    }

    @Override
    public Refreshable refresh() {
        dependencies.refresh();
        return this;
    }
}
