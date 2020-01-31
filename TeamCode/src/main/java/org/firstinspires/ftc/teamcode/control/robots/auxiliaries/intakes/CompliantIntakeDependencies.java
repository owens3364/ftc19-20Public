package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.intakes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;
import org.firstinspires.ftc.teamcode.utils.ObjectPair;

public class CompliantIntakeDependencies implements CompliantIntakeDependenciesI, Refreshable {
    private static final String PITCH_SERVO_A_NAME = "PITCHA";
    private static final String PITCH_SERVO_B_NAME = "PITCHB";
    private static final String LEFT_SERVO_NAME = "INTAKE_LEFT";
    private static final String RIGHT_SERVO_NAME = "INTAKE_RIGHT";
    private static final String INTAKE_DRIVE_A_NAME = "INTAKEA";
    private static final String INTAKE_DRIVE_B_NAME = "INTAKEB";

    private static final DcMotorSimple.Direction INTAKE_DRIVE_A_DIRECTION = DcMotorSimple.Direction.FORWARD;
    private static final DcMotorSimple.Direction INTAKE_DRIVE_B_DIRECTION = DcMotorSimple.Direction.REVERSE;

    private static final byte DEFAULT_PRIORITY = 0xA;

    private String pitchServoAName;
    private String pitchServoBName;
    private String leftServoName;
    private String rightServoName;
    private String intakeDriveAName;
    private String intakeDriveBName;

    private DcMotorSimple.Direction intakeDriveADirection;
    private DcMotorSimple.Direction intakeDriveBDirection;

    private Servo pitchServoA;
    private Servo pitchServoB;
    private Servo leftServo;
    private Servo rightServo;
    private DcMotor intakeDriveA;
    private DcMotor intakeDriveB;

    private HardwareMap map;

    private byte priority = -1;

    public CompliantIntakeDependencies setPitchServoAName(String name) {
        pitchServoAName = name;
        return this;
    }

    public CompliantIntakeDependencies setPitchServoBName(String name) {
        pitchServoBName = name;
        return this;
    }

    public CompliantIntakeDependencies setLeftServoName(String name) {
        leftServoName = name;
        return this;
    }

    public CompliantIntakeDependencies setRightServoName(String name) {
        rightServoName = name;
        return this;
    }

    public CompliantIntakeDependencies setIntakeDriveAName(String name) {
        intakeDriveAName = name;
        return this;
    }

    public CompliantIntakeDependencies setIntakeDriveBName(String name) {
        intakeDriveBName = name;
        return this;
    }

    public CompliantIntakeDependencies setIntakeDriveADirection(DcMotorSimple.Direction direction) {
        intakeDriveADirection = direction;
        return this;
    }

    public CompliantIntakeDependencies setIntakeDriveBDirection(DcMotorSimple.Direction direction) {
        intakeDriveBDirection = direction;
        return this;
    }

    public CompliantIntakeDependencies setPitchServoA(Servo servo) {
        pitchServoA = servo;
        return this;
    }

    public CompliantIntakeDependencies setPitchServoB(Servo servo) {
        pitchServoB = servo;
        return this;
    }

    public CompliantIntakeDependencies setLeftServo(Servo servo) {
        leftServo = servo;
        return this;
    }

    public CompliantIntakeDependencies setRightServo(Servo servo) {
        rightServo = servo;
        return this;
    }

    public CompliantIntakeDependencies setIntakeDriveA(DcMotor motor) {
        intakeDriveA = motor;
        return this;
    }

    public CompliantIntakeDependencies setIntakeDriveB(DcMotor motor) {
        intakeDriveB = motor;
        return this;
    }

    public CompliantIntakeDependencies setPriority(byte priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public ObjectPair<String, String> getPitchServoNames() {
        return new ObjectPair<>(pitchServoAName, pitchServoBName);
    }

    @Override
    public ObjectPair<Servo, Servo> getPitchServos() {
        return new ObjectPair<>(pitchServoA, pitchServoB);
    }

    @Override
    public String getLeftServoName() {
        return leftServoName;
    }

    @Override
    public Servo getLeftServo() {
        return leftServo;
    }

    @Override
    public String getRightServoName() {
        return rightServoName;
    }

    @Override
    public Servo getRightServo() {
        return rightServo;
    }

    @Override
    public ObjectPair<String, String> getIntakeDriveNames() {
        return new ObjectPair<>(intakeDriveAName, intakeDriveBName);
    }

    @Override
    public ObjectPair<DcMotorSimple.Direction, DcMotorSimple.Direction> getIntakeDriveDirections() {
        return new ObjectPair<>(intakeDriveADirection, intakeDriveBDirection);
    }

    @Override
    public ObjectPair<DcMotor, DcMotor> getIntakeDrives() {
        return new ObjectPair<>(intakeDriveA, intakeDriveB);
    }

    @Override
    public HardwareMap getHardwareMap() {
        return map;
    }

    @Override
    public DependencySupplier setHardwareMap(HardwareMap map) {
        this.map = map;
        return this;
    }

    @Override
    public void resolveDependencies() {
        if (pitchServoA == null) {
            if (pitchServoAName != null) {
                pitchServoA = map.servo.get(pitchServoAName);
            } else {
                pitchServoA = map.servo.get(PITCH_SERVO_A_NAME);
                pitchServoAName = PITCH_SERVO_A_NAME;
            }
        }
        if (pitchServoB == null) {
            if (pitchServoBName != null) {
                pitchServoB = map.servo.get(pitchServoBName);
            } else {
                pitchServoB = map.servo.get(PITCH_SERVO_B_NAME);
                pitchServoBName = PITCH_SERVO_B_NAME;
            }
        }
        if (leftServo == null) {
            if (leftServoName != null) {
                leftServo = map.servo.get(leftServoName);
            } else {
                leftServo = map.servo.get(LEFT_SERVO_NAME);
                leftServoName = LEFT_SERVO_NAME;
            }
        }
        if (rightServo == null) {
            if (rightServoName != null) {
                rightServo = map.servo.get(rightServoName);
            } else {
                rightServo = map.servo.get(RIGHT_SERVO_NAME);
                rightServoName = RIGHT_SERVO_NAME;
            }
        }
        if (intakeDriveA == null) {
            if (intakeDriveAName != null) {
                intakeDriveA = map.dcMotor.get(intakeDriveAName);
            } else {
                intakeDriveA = map.dcMotor.get(INTAKE_DRIVE_A_NAME);
                intakeDriveAName = INTAKE_DRIVE_A_NAME;
            }
        }
        if (intakeDriveB == null) {
            if (intakeDriveBName != null) {
                intakeDriveB = map.dcMotor.get(intakeDriveBName);
            } else {
                intakeDriveB = map.dcMotor.get(INTAKE_DRIVE_B_NAME);
                intakeDriveBName = INTAKE_DRIVE_B_NAME;
            }
        }
        setModes();
    }

    @Override
    public void resolveDependencies(HardwareMap map) {
        this.map = map;
        resolveDependencies();
    }

    @Override
    public int getPriority() {
        if (priority == -1) priority = DEFAULT_PRIORITY;
        return priority;
    }

    private void setModes() {
        intakeDriveA.setDirection(intakeDriveADirection != null ? intakeDriveADirection : INTAKE_DRIVE_A_DIRECTION);
        intakeDriveB.setDirection(intakeDriveBDirection != null ? intakeDriveBDirection : INTAKE_DRIVE_B_DIRECTION);
        intakeDriveA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeDriveB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeDriveA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeDriveB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public Refreshable refresh() {
        setModes();
        return this;
    }
}
