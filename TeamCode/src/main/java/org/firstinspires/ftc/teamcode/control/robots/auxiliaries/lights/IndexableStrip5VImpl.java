package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.lights;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class IndexableStrip5VImpl implements IndexableStrip5V {
    private final RevBlinkinLedDriver driver;
    private RevBlinkinLedDriver.BlinkinPattern pattern;

    public IndexableStrip5VImpl(String name, HardwareMap map) {
        driver = map.get(RevBlinkinLedDriver.class, name);
    }

    @Override
    public RevBlinkinLedDriver.BlinkinPattern getPattern() {
        return pattern;
    }

    @Override
    public void setPattern(RevBlinkinLedDriver.BlinkinPattern pattern) {
        this.pattern = pattern;
        driver.setPattern(pattern);
    }

    @Override
    public Refreshable refresh() {
        return this;
    }
}
