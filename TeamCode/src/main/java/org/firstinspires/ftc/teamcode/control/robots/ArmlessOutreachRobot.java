package org.firstinspires.ftc.teamcode.control.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.drivetrains.standard.StandardDependenciesI;
import org.firstinspires.ftc.teamcode.control.robots.drivetrains.standard.StandardDrive;
import org.firstinspires.ftc.teamcode.control.robots.drivetrains.standard.StandardDriveI;
import org.firstinspires.ftc.teamcode.control.robots.drivetrains.standard.StandardDriveInputI;
import org.firstinspires.ftc.teamcode.control.robots.drivetrains.standard.StandardDrivetrainI;

public class ArmlessOutreachRobot implements StandardDriveI {
    private final StandardDriveI standardDrive;

    public static ArmlessOutreachRobot instantiate(HardwareMap map) {
        return new ArmlessOutreachRobot(new StandardDrive(map));
    }

    public static ArmlessOutreachRobot instantiateWithDependencies(HardwareMap map, StandardDependenciesI dependencies) {
        return new ArmlessOutreachRobot(new StandardDrive(map, dependencies));
    }

    ArmlessOutreachRobot(StandardDriveI standardDrive) {
        this.standardDrive = standardDrive;
    }

    @Override
    public StandardDrivetrainI driveBySticks(StandardDriveInputI input) {
        return standardDrive.driveBySticks(input);
    }

    @Override
    public StandardDrivetrainI all(double power) {
        return standardDrive.all(power);
    }

    @Override
    public StandardDrivetrainI only(double... powers) {
        return standardDrive.only(powers);
    }
}
