package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robots.ArmfulOutreachRobot;

@TeleOp(name="ArmfulOutreachBotStandard")
public class ArmfulOutreachDemoStandard extends ArmlessOutreachDemoStandard {

    private ArmfulOutreachRobot privateRobot;

    private double pitchPosition = 0.0;
    private double yawPosition = 0.0;
    private double gripperPosition = 0.0;

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

        gripperPosition += gamepad2.right_stick_y;
        gripperPosition = gripperPosition < 0 ? 0.0 : gripperPosition > 1 ? 1.0 : gripperPosition;

        yawPosition += gamepad2.left_stick_x;
        yawPosition = yawPosition < 0 ? 0.0 : yawPosition > 1 ? 1.0 : yawPosition;

        pitchPosition += gamepad2.left_stick_y;
        pitchPosition = pitchPosition < 0 ? 0.0 : pitchPosition > 1 ? 1.0 : pitchPosition;

        privateRobot.setGripperPosition(gripperPosition);
        privateRobot.setYawPosition(yawPosition);
        privateRobot.setPitchPosition(pitchPosition);
    }

    @Override
    public void stop() {
        super.stop();
    }
}
