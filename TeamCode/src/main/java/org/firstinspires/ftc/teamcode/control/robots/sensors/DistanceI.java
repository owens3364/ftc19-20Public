package org.firstinspires.ftc.teamcode.control.robots.sensors;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public interface DistanceI {
    DistanceUnit getUnit();
    void setUnit(DistanceUnit unit);
    double getDistance();
    String getUnitName();
}
