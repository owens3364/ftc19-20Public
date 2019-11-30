package org.firstinspires.ftc.teamcode.robots.sensors;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public interface DistanceI {
    void setUnit(DistanceUnit unit);
    double getDistance();
}
