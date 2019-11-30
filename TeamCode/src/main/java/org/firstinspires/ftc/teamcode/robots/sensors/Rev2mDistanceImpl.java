package org.firstinspires.ftc.teamcode.robots.sensors;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Rev2mDistanceImpl implements DistanceI {
    private Rev2mDistanceSensor sensor;
    private DistanceUnit unit;

    public Rev2mDistanceImpl(String name, HardwareMap map) {
        this.sensor = map.get(Rev2mDistanceSensor.class, name);
        sensor.initialize();
    }

    @Override
    public void setUnit(DistanceUnit unit) {
        this.unit = unit != null ? unit : this.unit;
    }

    @Override
    public double getDistance() {
        return sensor.getDistance(unit);
    }
}
