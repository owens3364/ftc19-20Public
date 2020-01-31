package org.firstinspires.ftc.teamcode.control.robots.sensors.distance;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class DistanceDependencies implements DistanceDependenciesI {
    private static final String DISTANCE_NAME = "Distance";
    private static final byte DEFAULT_PRIORITY = 0xA;

    private String name;

    private Rev2mDistanceSensor sensor;

    private byte prioity = -1;

    private HardwareMap map;

    public DistanceDependencies setName(String name) {
        this.name = name;
        return this;
    }

    public DistanceDependencies setSensor(Rev2mDistanceSensor sensor) {
        this.sensor = sensor;
        return this;
    }

    public DistanceDependencies setPriority(byte priority) {
        this.prioity = priority;
        return this;
    }

    @Override
    public String getSensorName() {
        return name;
    }

    @Override
    public Rev2mDistanceSensor getSensor() {
        return sensor;
    }

    @Override
    public HardwareMap getHardwareMap() {
        return map;
    }

    @Override
    public DependencySupplier setHardwareMap(HardwareMap map) {
        this.map = map;
        return this;
    }

    @Override
    public void resolveDependencies() {
        if (name == null) name = DISTANCE_NAME;
        if (sensor == null) sensor = map.get(Rev2mDistanceSensor.class, name);
        sensor.initialize();
    }

    @Override
    public void resolveDependencies(HardwareMap map) {
        this.map = map;
        resolveDependencies();
    }

    @Override
    public int getPriority() {
        if (prioity == -1) prioity = DEFAULT_PRIORITY;
        return prioity;
    }

    @Override
    public Refreshable refresh() {
        resolveDependencies();
        return this;
    }
}
