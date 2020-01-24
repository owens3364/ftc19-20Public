package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.lights;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface IndexableStrip5V extends Refreshable {
    RevBlinkinLedDriver.BlinkinPattern getPattern();
    void setPattern(RevBlinkinLedDriver.BlinkinPattern pattern);
}
