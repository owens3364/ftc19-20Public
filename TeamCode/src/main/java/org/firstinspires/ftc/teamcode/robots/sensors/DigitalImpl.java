package org.firstinspires.ftc.teamcode.robots.sensors;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DigitalImpl implements DigitalI {

    DigitalChannel sensor;

    public DigitalImpl(String name, HardwareMap map) {
        sensor = map.digitalChannel.get(name);
    }

    @Override
    public boolean getHigh() {
        sensor.setMode(DigitalChannel.Mode.INPUT);
        return sensor.getState();
    }

    @Override
    public void setHigh(boolean high) {
        sensor.setMode(DigitalChannel.Mode.OUTPUT);
        sensor.setState(high);
    }
}