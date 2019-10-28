package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDependenciesI;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDrive;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDriveI;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDriveInputI;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDrivetrainI;

public class CompetitionRobot implements MecanumDriveI {
    private final MecanumDriveI mecanumDrive;

    public static CompetitionRobot instantiate(HardwareMap map) {
        return new CompetitionRobot(new MecanumDrive(map));
    }

    public static CompetitionRobot instantiateWithDependencies(HardwareMap map, MecanumDependenciesI dependencies) {
        return new CompetitionRobot(new MecanumDrive(map, dependencies));
    }

    private CompetitionRobot(MecanumDriveI mecanumDrive) {
        this.mecanumDrive = mecanumDrive;
    }

    @Override
    public MecanumDrivetrainI driveBySticks(MecanumDriveInputI input) {
        return mecanumDrive.driveBySticks(input);
    }

    @Override
    public MecanumDrivetrainI all(double power) {
        return mecanumDrive.all(power);
    }

    @Override
    public MecanumDrivetrainI only(double[] powers) {
        return mecanumDrive.only(powers);
    }
}
