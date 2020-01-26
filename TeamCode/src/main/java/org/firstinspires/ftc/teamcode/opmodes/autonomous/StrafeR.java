package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

@Autonomous(name="StrafeR")
public class StrafeR extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotor fl = hardwareMap.dcMotor.get("FL");
        DcMotor fr = hardwareMap.dcMotor.get("FR");
        DcMotor rl = hardwareMap.dcMotor.get("RL");
        DcMotor rr = hardwareMap.dcMotor.get("RR");
        Servo pitch = hardwareMap.servo.get("PITCH");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        rl.setDirection(DcMotorSimple.Direction.REVERSE);
        rr.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        FtcRobotControllerActivity.soloInstance().setOpModeScheduler(Common.scheduleTransitionToTeleOp(this));

        pitch.setPosition(1);

        sleep(2000);

        fl.setPower(.5);
        fr.setPower(-.5);
        rl.setPower(-.5);
        rr.setPower(.5);

        sleep(700);

        fl.setPower(.52);
        fr.setPower(.52);
        rl.setPower(.52);
        rr.setPower(.52);

        sleep(750);

        fl.setPower(0);
        fr.setPower(0);
        rl.setPower(0);
        rr.setPower(0);
    }
}