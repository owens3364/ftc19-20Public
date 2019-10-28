package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDriveInput;

@TeleOp(name="CompetitionRobot")
public class CompetitionTeleOp extends OpMode {
    CompetitionRobot robot;

    @Override
    public void init() {
        robot = CompetitionRobot.instantiate(hardwareMap);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        robot.driveBySticks(new MecanumDriveInput(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x));
    }

    @Override
    public void stop() {

    }
}
