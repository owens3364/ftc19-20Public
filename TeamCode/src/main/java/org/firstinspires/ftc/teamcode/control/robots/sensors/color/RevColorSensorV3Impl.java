package org.firstinspires.ftc.teamcode.control.robots.sensors.color;

import com.qualcomm.hardware.broadcom.BroadcomColorSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public class RevColorSensorV3Impl implements ColorI {
    private final RevColorSensorV3 sensor;
    private final RevColorSensorV3DependenciesI dependencies;

    private DistanceUnit unit = DistanceUnit.MM;

    public RevColorSensorV3Impl(HardwareMap map) {
        this(map, new RevColorSensorV3Dependencies());
    }

    public RevColorSensorV3Impl(HardwareMap map, RevColorSensorV3DependenciesI dependencies) {
        this.dependencies = dependencies;
        dependencies.resolveDependencies(map);
        sensor = dependencies.getSensor();
    }

    @Override
    public double getRed() {
        return sensor.getNormalizedColors().red;
    }

    @Override
    public double getGreen() {
        return sensor.getNormalizedColors().green;
    }

    @Override
    public double getBlue() {
        return sensor.getNormalizedColors().blue;
    }

    @Override
    public double getNormalizedRed() {
        NormalizedRGBA colors = sensor.getNormalizedColors();
        colors.red /= Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
        return colors.red;
    }

    @Override
    public double getNormalizedGreen() {
        NormalizedRGBA colors = sensor.getNormalizedColors();
        colors.green /= Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
        return colors.green;
    }

    @Override
    public double getNormalizedBlue() {
        NormalizedRGBA colors = sensor.getNormalizedColors();
        colors.blue /= Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
        return colors.blue;
    }

    @Override
    public double getLight() {
        return sensor.getLightDetected();
    }

    @Override
    public double getRawLight() {
        return sensor.getRawLightDetected();
    }

    @Override
    public double getNormalizedLight() {
        return getRawLight() / sensor.getRawLightDetectedMax();
    }

    @Override
    public DistanceUnit getUnit() {
        return unit;
    }

    @Override
    public void setUnit(DistanceUnit unit) {
        this.unit = unit != null ? unit : this.unit;
    }

    @Override
    public double getDistance() {
        return sensor.getDistance(unit);
    }

    @Override
    public String getUnitName() {
        return unit.name();
    }

    @Override
    public Refreshable refresh() {
        dependencies.refresh();
        return this;
    }
}
