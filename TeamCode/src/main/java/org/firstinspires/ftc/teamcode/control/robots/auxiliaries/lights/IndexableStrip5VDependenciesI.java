package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.lights;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface IndexableStrip5VDependenciesI extends DependencySupplier, Refreshable {
    String getDriverName();
    RevBlinkinLedDriver getDriver();
}
