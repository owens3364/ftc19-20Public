package org.firstinspires.ftc.teamcode.control.robots.sensors;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface DistanceI extends Refreshable {
    DistanceUnit getUnit();
    void setUnit(DistanceUnit unit);
    double getDistance();
    String getUnitName();
}
