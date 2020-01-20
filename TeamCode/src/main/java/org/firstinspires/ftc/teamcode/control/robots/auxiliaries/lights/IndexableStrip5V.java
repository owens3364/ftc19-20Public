package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.lights;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

public interface IndexableStrip5V {
    RevBlinkinLedDriver.BlinkinPattern getPattern();
    void setPattern(RevBlinkinLedDriver.BlinkinPattern pattern);
}
