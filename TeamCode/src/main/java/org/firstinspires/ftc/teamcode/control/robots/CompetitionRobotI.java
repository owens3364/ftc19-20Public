package org.firstinspires.ftc.teamcode.control.robots;

import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider.SliderArmDiagnostics;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider.SliderArmI;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.grippers.gripperV2.GripperDiagnostics;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.grippers.gripperV2.GripperI;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.intakes.CompliantIntakeDiagnostics;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.intakes.CompliantIntakeI;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.lights.IndexableStrip5V;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.lights.IndexableStrip5VDiagnostics;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos.AuxiliaryArmsDiagnostics;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos.AuxiliaryArmsI;
import org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum.MecanumDiagnostics;
import org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum.MecanumDriveI;
import org.firstinspires.ftc.teamcode.control.robots.sensors.ColorI;

public interface CompetitionRobotI extends MecanumDriveI, SliderArmI, AuxiliaryArmsI, GripperI, CompliantIntakeI, ColorI, IndexableStrip5V, RobotEssentials, MecanumDiagnostics, SliderArmDiagnostics, AuxiliaryArmsDiagnostics, GripperDiagnostics, CompliantIntakeDiagnostics, IndexableStrip5VDiagnostics {
    double getLiftHeight();
    double getDistanceLeft();
    double getDistanceRight();
    double getDistanceFront();
    double getDistanceRear();
    boolean isTouchPressed();
}