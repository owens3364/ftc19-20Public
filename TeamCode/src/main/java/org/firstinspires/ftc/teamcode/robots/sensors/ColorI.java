package org.firstinspires.ftc.teamcode.robots.sensors;

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
