package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.slider.SliderArm;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.slider.SliderArmDependencies;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.slider.SliderArmDependenciesI;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.arms.slider.SliderArmI;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers.Gripper;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers.GripperDependenciesI;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.grippers.GripperI;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.raw.servos.StatefulServo;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.raw.servos.StatefulServoDependencies;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.raw.servos.StatefulServoDependenciesI;
import org.firstinspires.ftc.teamcode.robots.auxiliaries.raw.servos.StatefulServoI;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDependenciesI;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDrive;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDriveI;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDriveInputI;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDrivetrainI;
import org.firstinspires.ftc.teamcode.robots.sensors.ColorI;
import org.firstinspires.ftc.teamcode.robots.sensors.DigitalI;
import org.firstinspires.ftc.teamcode.robots.sensors.DigitalImpl;
import org.firstinspires.ftc.teamcode.robots.sensors.DistanceI;
import org.firstinspires.ftc.teamcode.robots.sensors.Rev2mDistanceImpl;
import org.firstinspires.ftc.teamcode.robots.sensors.RevColorSensorV3Impl;

/*
CONFIGURATION
Front left drive motor:    FL         Port: 0    Bus: Motors    Hub: 1
Front right drive motor:   FR         Port: 1    Bus: Motors    Hub: 1
Rear left drive motor:     RL         Port: 2    Bus: Motors    Hub: 1
Rear right drive motor:    RR         Port: 3    Bus: Motors    Hub: 1
Left lift drive motor:     S0         Port: 1    Bus: Motors    Hub: 2
Right lift drive motor:    S1         Port: 0    Bus: Motors    Hub: 2
Left auxiliary arm servo:  AUXL       Port: 0    Bus: Servos    Hub: 2
Right auxiliary arm servo: AUXR       Port: 0    Bus: Servos    Hub: 1
Gripper pitch servo:       PITCH      Port: 1    Bus: Servos    Hub: 2
Gripper yaw servo:         YAW        Port: 2    Bus: Servos    Hub: 2
Gripper gripper servo:     GRIPPER    Port: 3    Bus: Servos    Hub: 2
Left distance sensor:      DistanceL  Port: 0    Bus: I2C Bus 1 Hub: 2
Right distance sensor:     DistanceR  Port: 0    Bus: I2C Bus 1 Hub: 1
Lift distance sensor:      LiftHeight Port: 1    Bus: I2C Bus 2 Hub: 2
Left limit switch:         UpperLimit Port: 0    Bus: Digital 0 Hub: 2
Right limit switch:        LowerLimit Port: 0    Bus: Digital 0 Hub: 2
Color sensor:              Color      Port: 0    Bus: I2C Bus 2 Hub: 1
UVC Camera:                Cam        Port: 1    Bus: NA        Hub: UltraUSB Hub
*/

public class CompetitionRobot implements CompetitionRobotI {

    private static final String FRONT_LEFT_DRIVE_NAME = "FL";
    private static final String FRONT_RIGHT_DRIVE_NAME = "FR";
    private static final String REAR_LEFT_DRIVE_NAME = "RL";
    private static final String REAR_RIGHT_DRIVE_NAME = "RR";
    private static final String LEFT_LIFT_DRIVE_NAME = "S0";
    private static final String RIGHT_LIFT_DRIVE_NAME = "S1";

    private static final String LEFT_AUXILIARY_ARM_SERVO_NAME = "AUXL";
    private static final String RIGHT_AUXILIARY_ARM_SERVO_NAME = "AUXR";

    private static final String GRIPPER_PITCH_SERVO_NAME = "PITCH";
    private static final String GRIPPER_YAW_SERVO_NAME = "YAW";
    private static final String GRIPPER_GRIPPER_SERVO_NAME = "GRIPPER";

    private static final String LEFT_DISTANCE_NAME = "DistanceL";
    private static final String RIGHT_DISTANCE_NAME = "DistanceR";
    private static final String LIFT_HEIGHT_NAME = "LiftHeight";

    private static final String UPPER_LIMIT_NAME = "UpperLimit";
    private static final String LOWER_LIMIT_NAME = "LowerLimit";

    private static final String COLOR_NAME = "Color";

    private static final String CAMERA_NAME = "Cam";

    private static final String[] DRIVE_NAMES = new String[] {FRONT_LEFT_DRIVE_NAME, FRONT_RIGHT_DRIVE_NAME, REAR_LEFT_DRIVE_NAME, REAR_RIGHT_DRIVE_NAME, LEFT_LIFT_DRIVE_NAME, RIGHT_LIFT_DRIVE_NAME};
    private static final String[] SERVO_NAMES = new String[] {LEFT_AUXILIARY_ARM_SERVO_NAME, RIGHT_AUXILIARY_ARM_SERVO_NAME, GRIPPER_PITCH_SERVO_NAME, GRIPPER_YAW_SERVO_NAME, GRIPPER_GRIPPER_SERVO_NAME};

    private static final String FRONT_LEFT_DRIVE_VAR_NAME = "frontLeftDrive";
    private static final String FRONT_RIGHT_DRIVE_VAR_NAME = "frontRightDrive";
    private static final String REAR_LEFT_DRIVE_VAR_NAME = "rearLeftDrive";
    private static final String REAR_RIGHT_DRIVE_VAR_NAME = "rearRightDrive";
    private static final String LEFT_LIFT_DRIVE_VAR_NAME = "leftLiftDrive";
    private static final String RIGHT_LIFT_DRIVE_VAR_NAME = "rightLiftDrive";

    private static final String LEFT_AUXILIARY_ARM_SERVO_VAR_NAME = "leftAuxiliaryArmServo";
    private static final String RIGHT_AUXILIARY_ARM_SERVO_VAR_NAME = "rightAuxiliaryArmServo";

    private static final String GRIPPER_PITCH_SERVO_VAR_NAME = "gripperPitchServo";
    private static final String GRIPPER_YAW_SERVO_VAR_NAME = "gripperYawServo";
    private static final String GRIPPER_GRIPPER_SERVO_VAR_NAME = "gripperGripperServo";

    private static final String[] DRIVE_VAR_NAMES = new String[] {FRONT_LEFT_DRIVE_VAR_NAME, FRONT_RIGHT_DRIVE_VAR_NAME, REAR_LEFT_DRIVE_VAR_NAME, REAR_RIGHT_DRIVE_VAR_NAME, LEFT_LIFT_DRIVE_VAR_NAME, RIGHT_LIFT_DRIVE_VAR_NAME};
    private static final String[] SERVO_VAR_NAMES = new String[] {LEFT_AUXILIARY_ARM_SERVO_VAR_NAME, RIGHT_AUXILIARY_ARM_SERVO_VAR_NAME, GRIPPER_PITCH_SERVO_VAR_NAME, GRIPPER_YAW_SERVO_VAR_NAME, GRIPPER_GRIPPER_SERVO_VAR_NAME};

    private final MecanumDriveI mecanumDrive;
    private final SliderArmI sliderArm;
    private final StatefulServoI leftArm;
    private final StatefulServoI rightArm;
    private final GripperI gripper;

    private final DistanceI leftDistance;
    private final DistanceI rightDistance;
    private final DistanceI liftHeight;
    private final DigitalI upperLimit;
    private final DigitalI lowerLimit;
    private final ColorI color;

    private final DistanceI[] unitfulDevices;

    public static CompetitionRobot instantiate(HardwareMap map) {
        return new CompetitionRobot(new MecanumDrive(map), new SliderArm(map, new SliderArmDependencies().setSliderDriveNames(LEFT_LIFT_DRIVE_NAME, RIGHT_LIFT_DRIVE_NAME).setSliderDriveDirections(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD)), new StatefulServo(map, new StatefulServoDependencies().setServoName(LEFT_AUXILIARY_ARM_SERVO_NAME)), new StatefulServo(map, new StatefulServoDependencies().setServoName(RIGHT_AUXILIARY_ARM_SERVO_NAME)), new Gripper(map), map);
    }

    public static CompetitionRobot instantiateWithDependencies(HardwareMap map, MecanumDependenciesI mecanumDependencies, SliderArmDependenciesI sliderArmDependencies, StatefulServoDependenciesI leftArmDependencies, StatefulServoDependenciesI rightArmDependencies, GripperDependenciesI gripperDependencies) {
        return new CompetitionRobot(new MecanumDrive(map, mecanumDependencies), new SliderArm(map, sliderArmDependencies), new StatefulServo(map, leftArmDependencies), new StatefulServo(map, rightArmDependencies), new Gripper(map, gripperDependencies), map);
    }

    private CompetitionRobot(MecanumDriveI mecanumDrive, SliderArmI sliderArm, StatefulServoI leftArm, StatefulServoI rightArm, GripperI gripper, HardwareMap map) {
        this.mecanumDrive = mecanumDrive;
        this.sliderArm = sliderArm;
        this.leftArm = leftArm;
        this.rightArm = rightArm;
        this.gripper = gripper;

        this.leftDistance = new Rev2mDistanceImpl(LEFT_DISTANCE_NAME, map);
        this.rightDistance = new Rev2mDistanceImpl(RIGHT_DISTANCE_NAME, map);
        this.liftHeight = new Rev2mDistanceImpl(LIFT_HEIGHT_NAME, map);
        this.upperLimit = new DigitalImpl(UPPER_LIMIT_NAME, map);
        this.lowerLimit = new DigitalImpl(LOWER_LIMIT_NAME, map);
        this.color = new RevColorSensorV3Impl(COLOR_NAME, map);

        unitfulDevices = new DistanceI[] { leftDistance, rightDistance, liftHeight, color };
    }

    @Override
    public MecanumDrivetrainI driveBySticks(MecanumDriveInputI input) {
        return mecanumDrive.driveBySticks(input);
    }

    @Override
    public MecanumDrivetrainI all(double power) {
        return mecanumDrive.all(power);
    }

    @Override
    public MecanumDrivetrainI only(double[] powers) {
        return mecanumDrive.only(powers);
    }

    @Override
    public void setLiftPower(double power) {
        sliderArm.setLiftPower(power);
    }

    @Override
    public double getLiftPosition() {
        return sliderArm.getLiftPosition();
    }

    @Override
    public void setLiftPosition(int ticks) {
        sliderArm.setLiftPosition(ticks);
    }

    @Override
    public DcMotor[] getLiftDrives() {
        return sliderArm.getLiftDrives();
    }

    @Override
    public double getLeftServoPosition() {
        return leftArm.getPosition();
    }

    @Override
    public void setLeftServoPosition(double position) {
        leftArm.setPosition(position);
    }

    @Override
    public void incrLeft(double incr) {
        leftArm.incr(incr);
    }

    @Override
    public Servo getLeftServoA() {
        return leftArm.getServo();
    }

    @Override
    public double getRightServoPosition() {
        return rightArm.getPosition();
    }

    @Override
    public void setRightServoPosition(double position) {
        rightArm.setPosition(position);
    }

    @Override
    public void incrRight(double incr) {
        rightArm.incr(incr);
    }

    @Override
    public Servo getRightServoA() {
        return rightArm.getServo();
    }


    @Override
    public double getPitchPosition() {
        return gripper.getPitchPosition();
    }

    @Override
    public void setPitchPosition(double position) {
        gripper.setPitchPosition(position);
    }

    @Override
    public double getYawPosition() {
        return gripper.getYawPosition();
    }

    @Override
    public void setYawPosition(double position) {
        gripper.setYawPosition(position);
    }

    @Override
    public double getGripperPosition() {
        return gripper.getGripperPosition();
    }

    @Override
    public void setGripperPosition(double position) {
        gripper.setGripperPosition(position);
    }

    @Override
    public Servo getPitch() {
        return gripper.getPitch();
    }

    @Override
    public Servo getYaw() {
        return gripper.getYaw();
    }

    @Override
    public Servo getGripper() {
        return gripper.getGripper();
    }

    @Override
    public String[] getMotorVarNames() {
        return DRIVE_VAR_NAMES;
    }

    @Override
    public String[] getServoVarNames() {
        return SERVO_VAR_NAMES;
    }

    @Override
    public String[] getMotorNames() {
        return DRIVE_NAMES;
    }

    @Override
    public String[] getServoNames() {
        return SERVO_NAMES;
    }

    @Override
    public double getLiftHeight() {
        return liftHeight.getDistance();
    }

    @Override
    public double getDistanceL() {
        return leftDistance.getDistance();
    }

    @Override
    public double getDistanceR() {
        return rightDistance.getDistance();
    }

    @Override
    public boolean getUpperLimit() {
        return upperLimit.getHigh();
    }

    @Override
    public boolean getLowerLimit() {
        return lowerLimit.getHigh();
    }

    @Override
    public double getRed() {
        return color.getRed();
    }

    @Override
    public double getGreen() {
        return color.getGreen();
    }

    @Override
    public double getBlue() {
        return color.getBlue();
    }

    @Override
    public double getNormalizedRed() {
        return color.getNormalizedRed();
    }

    @Override
    public double getNormalizedGreen() {
        return color.getNormalizedGreen();
    }

    @Override
    public double getNormalizedBlue() {
        return color.getNormalizedBlue();
    }

    @Override
    public double getLight() {
        return color.getLight();
    }

    @Override
    public double getRawLight() {
        return color.getRawLight();
    }

    @Override
    public double getNormalizedLight() {
        return color.getNormalizedLight();
    }

    @Override
    public void setUnit(DistanceUnit unit) {
        for (DistanceI distance : unitfulDevices) {
            distance.setUnit(unit);
        }
    }

    @Override
    public double getDistance() {
        return color.getDistance();
    }
}