package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider;

import com.qualcomm.robotcore.hardware.DcMotor;

public interface SliderArmI {
    void setLiftPower(double power);
    double getLiftPosition();
    void setLiftPosition(int ticks);
    DcMotor[] getLiftDrives();
}
