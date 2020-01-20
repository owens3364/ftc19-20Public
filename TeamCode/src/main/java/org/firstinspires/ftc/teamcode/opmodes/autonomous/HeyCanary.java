package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="HEYCANARY")
public class HeyCanary extends LinearOpMode {
    @Override
    public void runOpMode() {
        Log.w("INIT", "INIT");
        waitForStart();
        Log.w("START", "START");
    }
}
