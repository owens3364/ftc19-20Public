package org.firstinspires.ftc.teamcode.control.robots.sensors.digital;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class DigitalImpl implements DigitalI {
    private final DigitalChannel sensor;
    private final DigitalDependenciesI dependencies;

    public DigitalImpl(HardwareMap map) {
        this(map, new DigitalDependencies());
    }

    public DigitalImpl(HardwareMap map, DigitalDependenciesI dependencies) {
        this.dependencies = dependencies;
        dependencies.resolveDependencies(map);
        sensor = dependencies.getSensor();
    }

    @Override
    public boolean getHigh() {
        return !sensor.getState();
    }

    @Override
    public void setHigh(boolean high) {
        sensor.setState(high);
    }

    @Override
    public Refreshable refresh() {
        dependencies.resolveDependencies();
        return this;
    }
}
