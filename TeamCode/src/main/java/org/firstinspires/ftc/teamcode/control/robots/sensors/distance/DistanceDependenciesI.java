package org.firstinspires.ftc.teamcode.control.robots.sensors.distance;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface DistanceDependenciesI extends DependencySupplier, Refreshable {
    String getSensorName();
    Rev2mDistanceSensor getSensor();
}
