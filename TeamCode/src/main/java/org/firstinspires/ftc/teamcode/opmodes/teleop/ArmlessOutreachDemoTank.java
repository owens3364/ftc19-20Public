package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robots.ArmlessOutreachRobot;
import org.firstinspires.ftc.teamcode.robots.drivetrains.standard.StandardDriveInput;

@TeleOp(name="ArmlessOutreachBotTank")
public class ArmlessOutreachDemoTank extends OpMode {
    ArmlessOutreachRobot robot;

    @Override
    public void init() {
        robot = ArmlessOutreachRobot.instantiate(hardwareMap);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        robot.driveBySticks(new StandardDriveInput(true, (double) -gamepad1.left_stick_y, (double) -gamepad1.right_stick_y, null, null));
    }

    @Override
    public void stop() {

    }
}
