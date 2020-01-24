package org.firstinspires.ftc.teamcode.control.robots.sensors;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface DigitalI extends Refreshable {
    boolean getHigh();
    void setHigh(boolean high);
}