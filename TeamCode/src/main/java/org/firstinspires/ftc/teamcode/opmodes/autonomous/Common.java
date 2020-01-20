package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeManagerImpl;
import org.firstinspires.ftc.teamcode.annotations.Competition;
import org.firstinspires.ftc.teamcode.opmodes.teleop.ArmfulOutreachDemoStandard;
import org.firstinspires.ftc.teamcode.opmodes.teleop.ArmfulOutreachDemoTank;
import org.firstinspires.ftc.teamcode.opmodes.teleop.ArmlessOutreachDemoStandard;
import org.firstinspires.ftc.teamcode.opmodes.teleop.ArmlessOutreachDemoTank;
import org.firstinspires.ftc.teamcode.opmodes.teleop.CompetitionTeleOp;

import java.util.Objects;

class Common {
    private static final Class[] TELEOP_OPMODES = {
            ArmfulOutreachDemoStandard.class,
            ArmfulOutreachDemoTank.class,
            ArmlessOutreachDemoStandard.class,
            ArmlessOutreachDemoTank.class,
            CompetitionTeleOp.class
    };

    private static String opModeName;

    private static String getCompetitionOpModeName() {
        if (opModeName != null) {
            return opModeName;
        }
        for (Class teleOpOpMode : TELEOP_OPMODES) {
            if (teleOpOpMode.isAnnotationPresent(TeleOp.class) && teleOpOpMode.isAnnotationPresent(Competition.class)) {
                opModeName = ((TeleOp) Objects.requireNonNull(teleOpOpMode.getDeclaredAnnotation(TeleOp.class))).name();
            }
        }
        return opModeName;
    }

    static void transitionToTeleOp(OpMode currentOpMode) {
        ((OpModeManagerImpl) currentOpMode.internalOpModeServices).initActiveOpMode(getCompetitionOpModeName());
        new Thread(() -> {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException ignored) {}
            ((OpModeManagerImpl) currentOpMode.internalOpModeServices).startActiveOpMode();
        }).start();
    }
}
