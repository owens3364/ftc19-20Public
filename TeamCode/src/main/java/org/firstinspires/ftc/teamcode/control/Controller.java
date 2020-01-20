package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.robocol.RobocolParsable;

import org.firstinspires.ftc.robotcore.internal.ui.GamepadUser;

public interface Controller extends RobocolParsable {
    Gamepad getSrc();

    double leftStickX();
    double leftStickY();
    double rightStickX();
    double rightStickY();

    boolean dpadUp();
    boolean dpadDown();
    boolean dpadLeft();
    boolean dpadRight();

    boolean a();
    boolean b();
    boolean x();
    boolean y();

    boolean guide();
    boolean start();
    boolean back();

    boolean leftBumper();
    boolean rightBumper();

    boolean leftStickButton();
    boolean rightStickButton();

    double leftTrigger();
    double rightTrigger();

    GamepadUser getUser();
    void setUser(GamepadUser user);

    int getGamepadId();
    void setGamepadId(int id);

    long getTimestamp();
    void setTimestamp(long timestamp);
    void refreshTimestamp();

    void copy(Gamepad gamepad) throws RobotCoreException;

    void reset();

    void setJoystickDeadzone(float deadzone);

    void update(android.view.MotionEvent event);
    void udpate(android.view.KeyEvent event);

    boolean atRest();

    String type();

    String getName();
    void setName(String name);
}