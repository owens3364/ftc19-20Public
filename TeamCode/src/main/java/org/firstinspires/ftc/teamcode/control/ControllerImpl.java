package org.firstinspires.ftc.teamcode.control;

import android.view.KeyEvent;
import android.view.MotionEvent;

import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.internal.ui.GamepadUser;
import org.firstinspires.ftc.teamcode.annotations.Observable;

public class ControllerImpl implements Controller {
    private final Gamepad gamepad;

    private String name = "";

    public ControllerImpl(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public ControllerImpl(Gamepad gamepad, String name) {
        this(gamepad);
        this.name = name;
    }


    @Override
    public Gamepad getSrc() {
        return gamepad;
    }

    @Override
    @Observable(key = "Left Stick X")
    public double leftStickX() {
        return gamepad.left_stick_x;
    }

    @Override
    @Observable(key = "Left Stick Y")
    public double leftStickY() {
        return gamepad.left_stick_y;
    }

    @Override
    @Observable(key = "Right Stick X")
    public double rightStickX() {
        return gamepad.right_stick_x;
    }

    @Override
    @Observable(key = "Right Stick Y")
    public double rightStickY() {
        return gamepad.right_stick_y;
    }

    @Override
    @Observable(key = "Dpad Up")
    public boolean dpadUp() {
        return gamepad.dpad_up;
    }

    @Override
    @Observable(key = "Dpad Down")
    public boolean dpadDown() {
        return gamepad.dpad_down;
    }

    @Override
    @Observable(key = "Dpad Left")
    public boolean dpadLeft() {
        return gamepad.dpad_left;
    }

    @Override
    @Observable(key = "Dpad Right")
    public boolean dpadRight() {
        return gamepad.dpad_right;
    }

    @Override
    @Observable(key = "A")
    public boolean a() {
        return gamepad.a;
    }

    @Override
    @Observable(key = "B")
    public boolean b() {
        return gamepad.b;
    }

    @Override
    @Observable(key = "X")
    public boolean x() {
        return gamepad.x;
    }

    @Override
    @Observable(key = "Y")
    public boolean y() {
        return gamepad.y;
    }

    @Override
    public boolean guide() {
        return gamepad.guide;
    }

    @Override
    public boolean start() {
        return gamepad.start;
    }

    @Override
    public boolean back() {
        return gamepad.back;
    }

    @Override
    @Observable(key = "Left Bumper")
    public boolean leftBumper() {
        return gamepad.left_bumper;
    }

    @Override
    @Observable(key = "Right Bumper")
    public boolean rightBumper() {
        return gamepad.right_bumper;
    }

    @Override
    public boolean leftStickButton() {
        return gamepad.left_stick_button;
    }

    @Override
    public boolean rightStickButton() {
        return gamepad.right_stick_button;
    }

    @Override
    @Observable(key = "Left Trigger")
    public double leftTrigger() {
        return gamepad.left_trigger;
    }

    @Override
    @Observable(key = "Right Trigger")
    public double rightTrigger() {
        return gamepad.right_trigger;
    }

    @Override
    public GamepadUser getUser() {
        return gamepad.getUser();
    }

    @Override
    public void setUser(GamepadUser user) {
        gamepad.setUser(user);
    }

    @Override
    public int getGamepadId() {
        return gamepad.getGamepadId();
    }

    @Override
    public void setGamepadId(int id) {
        gamepad.setGamepadId(id);
    }

    @Override
    public long getTimestamp() {
        return gamepad.timestamp;
    }

    @Override
    public void setTimestamp(long timestamp) {
        gamepad.setTimestamp(timestamp);
    }

    @Override
    public void refreshTimestamp() {
        gamepad.refreshTimestamp();
    }

    @Override
    public void copy(Gamepad gamepad) throws RobotCoreException {
        gamepad.copy(gamepad);
    }

    @Override
    public void reset() {
        gamepad.reset();
    }

    @Override
    public void setJoystickDeadzone(float deadzone) {
        gamepad.setJoystickDeadzone(deadzone);
    }

    @Override
    public void update(MotionEvent event) {
        gamepad.update(event);
    }

    @Override
    public void udpate(KeyEvent event) {
        gamepad.update(event);
    }

    @Override
    public boolean atRest() {
        return gamepad.atRest();
    }

    @Override
    public String type() {
        return gamepad.type();
    }

    @Override
    public MsgType getRobocolMsgType() {
        return gamepad.getRobocolMsgType();
    }

    @Override
    public int getSequenceNumber() {
        return gamepad.getSequenceNumber();
    }

    @Override
    public void setSequenceNumber() {
        gamepad.setSequenceNumber();
    }

    @Override
    public boolean shouldTransmit(long nanotimeNow) {
        return gamepad.shouldTransmit(nanotimeNow);
    }

    @Override
    public byte[] toByteArrayForTransmission() throws RobotCoreException {
        return gamepad.toByteArrayForTransmission();
    }

    @Override
    public byte[] toByteArray() throws RobotCoreException {
        return gamepad.toByteArray();
    }

    @Override
    public void fromByteArray(byte[] byteArray) throws RobotCoreException {
        gamepad.fromByteArray(byteArray);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
