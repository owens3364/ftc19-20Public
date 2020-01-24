package org.firstinspires.ftc.teamcode.control.robots.sensors;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class Rev2mDistanceImpl implements DistanceI {
    private final Rev2mDistanceSensor sensor;
    private DistanceUnit unit;

    public Rev2mDistanceImpl(String name, HardwareMap map) {
        this.sensor = map.get(Rev2mDistanceSensor.class, name);
        sensor.initialize();
    }

    @Override
    public DistanceUnit getUnit() {
        return unit;
    }

    @Override
    public void setUnit(DistanceUnit unit) {
        this.unit = unit != null ? unit : this.unit;
    }

    @Override
    public double getDistance() {
        return sensor.getDistance(unit);
    }

    @Override
    public String getUnitName() {
        return unit.name();
    }

    @Override
    public Refreshable refresh() {
        sensor.initialize();
        return this;
    }
}
