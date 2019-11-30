package org.firstinspires.ftc.teamcode.robots;

import org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.slider.SliderArmI;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers.GripperI;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.raw.servos.AuxiliaryArmsI;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDriveI;
import org.firstinspires.ftc.teamcode.robots.sensors.ColorI;

public interface CompetitionRobotI extends MecanumDriveI, SliderArmI, AuxiliaryArmsI, GripperI, ColorI, RobotEssentials {
    double getLiftHeight();
    double getDistanceL();
    double getDistanceR();
    boolean getUpperLimit();
    boolean getLowerLimit();
}