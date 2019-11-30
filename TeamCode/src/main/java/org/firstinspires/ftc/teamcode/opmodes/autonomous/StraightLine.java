package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="StraightLine")
public class StraightLine extends LinearOpMode {

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

        pitch.setPosition(1);

        sleep(2000);

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