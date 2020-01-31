package org.firstinspires.ftc.teamcode.control.robots.sensors.color;

import com.qualcomm.hardware.rev.RevColorSensorV3;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface RevColorSensorV3DependenciesI extends DependencySupplier, Refreshable {
    String getSensorName();
    RevColorSensorV3 getSensor();
}
