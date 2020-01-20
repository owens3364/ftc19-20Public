package org.firstinspires.ftc.teamcode.control.robots.sensors;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DigitalImpl implements DigitalI {

    private final DigitalChannel sensor;

    public DigitalImpl(String name, HardwareMap map) {
        sensor = map.digitalChannel.get(name);
        sensor.setMode(DigitalChannel.Mode.INPUT);
    }

    public DigitalImpl(String name, HardwareMap map, DigitalChannel.Mode mode) {
        sensor = map.digitalChannel.get(name);
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
}
