package org.firstinspires.ftc.teamcode.control.robots.sensors.distance;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class Rev2mDistanceImpl implements DistanceI {
    private final Rev2mDistanceSensor sensor;
    private final DistanceDependenciesI dependencies;
    private DistanceUnit unit;

    public Rev2mDistanceImpl(HardwareMap map) {
        this(map, new DistanceDependencies());
    }

    public Rev2mDistanceImpl(HardwareMap map, DistanceDependenciesI dependencies) {
        this.dependencies = dependencies;
        dependencies.resolveDependencies(map);
        sensor = dependencies.getSensor();
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
        dependencies.refresh();
        return this;
    }
}
