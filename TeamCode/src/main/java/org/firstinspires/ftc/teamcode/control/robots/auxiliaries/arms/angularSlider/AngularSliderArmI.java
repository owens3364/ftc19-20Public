package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.angularSlider;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider.SliderArmI;

public interface AngularSliderArmI extends SliderArmI {
    void setAngularPower(double power);
    double getAngularPosition();
    void setAngularPosition(int ticks);
    DcMotor[] getAngularDrives();
}
