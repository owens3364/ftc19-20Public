package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

public interface SliderArmI extends Refreshable {
    void setLiftPower(double power);
    double getLiftPosition();
    void setLiftPosition(int ticks);
    DcMotor[] getLiftDrives();
}
