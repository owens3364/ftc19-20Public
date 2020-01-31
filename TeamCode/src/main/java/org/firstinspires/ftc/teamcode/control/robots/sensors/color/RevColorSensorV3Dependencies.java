package org.firstinspires.ftc.teamcode.control.robots.sensors.color;

import com.qualcomm.hardware.broadcom.BroadcomColorSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class RevColorSensorV3Dependencies implements RevColorSensorV3DependenciesI{
    private static final String COLOR_NAME = "Color";
    private static final byte DEFAULT_PRIORITY = 0xA;

    private String colorName;
    private RevColorSensorV3 sensor;

    private HardwareMap map;

    private byte priority = -1;

    public RevColorSensorV3Dependencies setName(String name) {
        this.colorName = name;
        return this;
    }

    public RevColorSensorV3Dependencies setSensor(RevColorSensorV3 sensor) {
        this.sensor = sensor;
        return this;
    }

    public RevColorSensorV3Dependencies setPriority(byte priority) {
        this.priority = priority;
        return this;
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
        if (colorName == null) colorName = COLOR_NAME;
        if (sensor == null) sensor = map.get(RevColorSensorV3.class, colorName);
        sensor.initialize(BroadcomColorSensor.Parameters.createForAPDS9151());
    }

    @Override
    public void resolveDependencies(HardwareMap map) {
        this.map = map;
        resolveDependencies();
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Refreshable refresh() {
        resolveDependencies();
        return this;
    }

    @Override
    public String getSensorName() {
        return colorName;
    }

    @Override
    public RevColorSensorV3 getSensor() {
        return sensor;
    }
}
