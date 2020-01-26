package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.teamcode.control.robots.CompetitionRobot;

@Autonomous(name="StraightLine")
public class StraightLine extends LinearOpMode {

    @Override
    public void runOpMode() {
        CompetitionRobot robot = CompetitionRobot.instantiate(hardwareMap);
        FtcRobotControllerActivity.soloInstance().setCachedRobotObject(robot);
        waitForStart();
        FtcRobotControllerActivity.soloInstance().setOpModeScheduler(Common.scheduleTransitionToTeleOp(this));
        robot.all(-.8);
        sleep(2000);
        robot.all(0);
    }
}