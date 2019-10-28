package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robots.ArmfulOutreachRobot;

@TeleOp(name="ArmfulOutreachBotTank")
public class ArmfulOutreachDemoTank extends ArmlessOutreachDemoTank {

    private ArmfulOutreachRobot privateRobot;

    @Override
    public void init() {
        privateRobot = ArmfulOutreachRobot.instantiate(hardwareMap);
        super.robot = privateRobot;
    }

    @Override
    public void init_loop() {
        super.init_loop();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        super.loop();
        privateRobot.setLiftPower(gamepad1.dpad_up ? 1 : (gamepad1.dpad_down ? -1 : 0));
        privateRobot.setAngularPower(gamepad1.left_trigger != 0 ? gamepad1.left_trigger : gamepad1.right_trigger != 0 ? -gamepad1.right_trigger : 0);
        privateRobot.setLeftPosition(gamepad2.left_stick_x);
        privateRobot.setRightPosition(gamepad2.right_stick_x);
    }

    @Override
    public void stop() {
        super.stop();
    }
}