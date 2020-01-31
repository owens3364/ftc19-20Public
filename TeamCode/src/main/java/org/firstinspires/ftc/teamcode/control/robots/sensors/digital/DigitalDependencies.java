package org.firstinspires.ftc.teamcode.control.robots.sensors.digital;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class DigitalDependencies implements DigitalDependenciesI {
    private static final String DIGITAL_NAME = "DGTL";
    private static final DigitalChannel.Mode DIGITAL_MODE = DigitalChannel.Mode.INPUT;
    private static final byte DEFAULT_PRIORITY = 0xA;

    private String sensorName;
    private DigitalChannel.Mode mode;
    private DigitalChannel sensor;

    private HardwareMap map;

    private byte priority = -1;

    public DigitalDependencies setName(String name) {
        this.sensorName = name;
        return this;
    }

    public DigitalDependencies setMode(DigitalChannel.Mode mode) {
        this.mode = mode;
        return this;
    }

    public DigitalDependencies setSensor(DigitalChannel sensor) {
        this.sensor = sensor;
        return this;
    }

    public DigitalDependencies setPriority(byte priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public String getSensorName() {
        return sensorName;
    }

    @Override
    public DigitalChannel.Mode getMode() {
        return mode;
    }

    @Override
    public DigitalChannel getSensor() {
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
        if (sensorName == null) sensorName = DIGITAL_NAME;
        if (mode == null) mode = DIGITAL_MODE;
        if (sensor == null) sensor = map.digitalChannel.get(sensorName);
        sensor.setMode(mode);
    }

    @Override
    public void resolveDependencies(HardwareMap map) {
        this.map = map;
        resolveDependencies();
    }

    @Override
    public int getPriority() {
        if (priority == -1) priority = DEFAULT_PRIORITY;
        return priority;
    }

    @Override
    public Refreshable refresh() {
        resolveDependencies();
        return this;
    }
}
