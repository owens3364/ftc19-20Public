package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.lights;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class IndexableStrip5VImpl implements IndexableStrip5V {
    private final RevBlinkinLedDriver driver;
    private RevBlinkinLedDriver.BlinkinPattern pattern;
    private final IndexableStrip5VDependenciesI dependencies;

    public IndexableStrip5VImpl(HardwareMap map) {
        this(map, new IndexableStrip5VDependencies());
    }

    public IndexableStrip5VImpl(HardwareMap map, IndexableStrip5VDependenciesI dependencies) {
        this.dependencies = dependencies;
        dependencies.resolveDependencies(map);
        driver = dependencies.getDriver();
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
        dependencies.refresh();
        return this;
    }
}
