package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.lights;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class IndexableStrip5VDependencies implements IndexableStrip5VDependenciesI {
    private static final String LIGHTS_NAME = "LETTHEREBELIGHT";
    private static final byte DEFAULT_PRIORITY = 0xA;

    private String lightsName;

    private RevBlinkinLedDriver driver;

    private HardwareMap map;

    private byte priority = -1;

    public IndexableStrip5VDependencies setName(String name) {
        this.lightsName = name;
        return this;
    }

    public IndexableStrip5VDependencies setDriver(RevBlinkinLedDriver driver) {
        this.driver = driver;
        return this;
    }

    public IndexableStrip5VDependencies setPriority(byte priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public String getDriverName() {
        return lightsName;
    }

    @Override
    public RevBlinkinLedDriver getDriver() {
        return driver;
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
        if (lightsName == null) lightsName = LIGHTS_NAME;
        if (driver == null) driver = map.get(RevBlinkinLedDriver.class, lightsName);
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
        return this;
    }
}
