package org.firstinspires.ftc.teamcode.control.robots.sensors.color;

import org.firstinspires.ftc.teamcode.control.robots.sensors.distance.DistanceI;

public interface ColorI extends DistanceI {
    double getRed();
    double getGreen();
    double getBlue();
    double getNormalizedRed();
    double getNormalizedGreen();
    double getNormalizedBlue();
    double getLight();
    double getRawLight();
    double getNormalizedLight();
}
