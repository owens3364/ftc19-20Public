package org.firstinspires.ftc.teamcode.control.robots.sensors.digital;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface DigitalDependenciesI extends DependencySupplier, Refreshable {
    String getSensorName();
    DigitalChannel.Mode getMode();
    DigitalChannel getSensor();
}
