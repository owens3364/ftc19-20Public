package org.firstinspires.ftc.teamcode.control.robots.sensors;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class DigitalImpl implements DigitalI {

    private final DigitalChannel sensor;
    private final DigitalChannel.Mode defaultMode;

    public DigitalImpl(String name, HardwareMap map) {
        sensor = map.digitalChannel.get(name);
        defaultMode = DigitalChannel.Mode.INPUT;
        sensor.setMode(defaultMode);
    }

    public DigitalImpl(String name, HardwareMap map, DigitalChannel.Mode mode) {
        sensor = map.digitalChannel.get(name);
        defaultMode = mode;
        sensor.setMode(mode);
    }

    @Override
    public boolean getHigh() {
        if (sensor.getMode() == DigitalChannel.Mode.OUTPUT) {
            sensor.setMode(DigitalChannel.Mode.INPUT);
        }
        return !sensor.getState();
    }

    @Override
    public void setHigh(boolean high) {
        if (sensor.getMode() == DigitalChannel.Mode.INPUT) {
            sensor.setMode(DigitalChannel.Mode.OUTPUT);
        }
        sensor.setState(high);
    }

    @Override
    public Refreshable refresh() {
        sensor.setMode(defaultMode);
        return this;
    }
}
