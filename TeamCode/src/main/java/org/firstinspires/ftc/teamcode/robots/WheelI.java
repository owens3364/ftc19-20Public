package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.hardware.DcMotor;

public interface WheelI {
    WheelPosition getPosition();
    DcMotor getMotor();
}
