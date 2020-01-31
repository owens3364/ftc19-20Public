package org.firstinspires.ftc.teamcode.control.robots;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.annotations.Observable;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider.SliderArm;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider.SliderArmDependencies;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider.SliderArmDependenciesI;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider.SliderArmI;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.grippers.gripperV2.Gripper;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.grippers.gripperV2.GripperDependenciesI;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.grippers.gripperV2.GripperI;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.lights.IndexableStrip5V;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.lights.IndexableStrip5VDependencies;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.lights.IndexableStrip5VImpl;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos.StatefulServo;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos.StatefulServoDependencies;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos.StatefulServoDependenciesI;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.raw.servos.StatefulServoI;
import org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum.MecanumDependenciesI;
import org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum.MecanumDrive;
import org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum.MecanumDriveI;
import org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum.MecanumDriveInputI;
import org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum.MecanumDrivetrainI;
import org.firstinspires.ftc.teamcode.control.robots.sensors.color.ColorI;
import org.firstinspires.ftc.teamcode.control.robots.sensors.color.RevColorSensorV3Dependencies;
import org.firstinspires.ftc.teamcode.control.robots.sensors.distance.DistanceDependencies;
import org.firstinspires.ftc.teamcode.control.robots.sensors.distance.DistanceI;
import org.firstinspires.ftc.teamcode.control.robots.sensors.distance.Rev2mDistanceImpl;
import org.firstinspires.ftc.teamcode.control.robots.sensors.color.RevColorSensorV3Impl;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/*
CONFIGURATION
Front left drive motor:    FL               Port: 0    Bus: Motors    Hub: 1
Front right drive motor:   FR               Port: 1    Bus: Motors    Hub: 1
Rear left drive motor:     RL               Port: 2    Bus: Motors    Hub: 1
Rear right drive motor:    RR               Port: 3    Bus: Motors    Hub: 1
Left lift drive motor:     S0               Port: 1    Bus: Motors    Hub: 2
Right lift drive motor:    S1               Port: 0    Bus: Motors    Hub: 2
Intake drive motor a:      INTAKEA          Port: 2    Bus: Motors    Hub: 2
Intake drive motor b:      INTAKEB          Port: 3    Bus: Motors    Hub: 2
Left auxiliary arm servo:  AUXL             Port: 0    Bus: Servos    Hub: 2
Right auxiliary arm servo: AUXR             Port: 0    Bus: Servos    Hub: 1
Gripper pitch servo:       PITCH            Port: 1    Bus: Servos    Hub: 2
Gripper gripper servo:     GRIPPER          Port: 2    Bus: Servos    Hub: 2
Intake pitch servo a:      PITCHA           Port: 3    Bus: Servos    Hub: 2
Intake pitch servo b:      PITCHB           Port: 4    Bus: Servos    Hub: 2
Intake left servo:         INTAKE_LEFT      Port: 1    Bus: Servos    Hub: 1
Intake right servo:        INTAKE_RIGHT     Port: 2    Bus: Servos    Hub: 1
Left distance sensor:      DistanceLeft     Port: 0    Bus: I2C Bus 1 Hub: 2
Right distance sensor:     DistanceRight    Port: 0    Bus: I2C Bus 1 Hub: 1
Front distance sensor:     DistanceFront    Port: 0    Bus: I2C Bus 2 Hub: 2
Rear distance sensor:      DistanceRear     Port: 0    Bus: I2C Bus 3 Hub: 2
Lift distance sensor:      LiftHeight       Port: 0    Bus: I2C Bus 3 Hub: 1
Color sensor:              Color            Port: 0    Bus: I2C Bus 2 Hub: 1
Lights:                    LETTHEREBELIGHT  Port: 3    Bus: Servos    Hub: 1
UVC Camera:                Cam              Port: 1    Bus: NA        Hub: UltraUSB Hub
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
    private static final String GRIPPER_GRIPPER_SERVO_NAME = "GRIPPER";

    private static final String LEFT_DISTANCE_NAME = "DistanceLeft";
    private static final String RIGHT_DISTANCE_NAME = "DistanceRight";
    private static final String FRONT_DISTANCE_NAME = "DistanceFront";
    private static final String REAR_DISTANCE_NAME = "DistanceRear";
    private static final String LIFT_HEIGHT_NAME = "LiftHeight";

    private static final String COLOR_NAME = "Color";

    private static final String LIGHTS_NAME = "LETTHEREBELIGHT";

    private static final String[] DRIVE_NAMES = new String[] {FRONT_LEFT_DRIVE_NAME, FRONT_RIGHT_DRIVE_NAME, REAR_LEFT_DRIVE_NAME, REAR_RIGHT_DRIVE_NAME, LEFT_LIFT_DRIVE_NAME, RIGHT_LIFT_DRIVE_NAME};
    private static final String[] SERVO_NAMES = new String[] {LEFT_AUXILIARY_ARM_SERVO_NAME, RIGHT_AUXILIARY_ARM_SERVO_NAME, GRIPPER_PITCH_SERVO_NAME, GRIPPER_GRIPPER_SERVO_NAME};

    private static final String FRONT_LEFT_DRIVE_VAR_NAME = "frontLeftDrive";
    private static final String FRONT_RIGHT_DRIVE_VAR_NAME = "frontRightDrive";
    private static final String REAR_LEFT_DRIVE_VAR_NAME = "rearLeftDrive";
    private static final String REAR_RIGHT_DRIVE_VAR_NAME = "rearRightDrive";
    private static final String LEFT_LIFT_DRIVE_VAR_NAME = "leftLiftDrive";
    private static final String RIGHT_LIFT_DRIVE_VAR_NAME = "rightLiftDrive";

    private static final String LEFT_AUXILIARY_ARM_SERVO_VAR_NAME = "leftAuxiliaryArmServo";
    private static final String RIGHT_AUXILIARY_ARM_SERVO_VAR_NAME = "rightAuxiliaryArmServo";

    private static final String GRIPPER_PITCH_SERVO_VAR_NAME = "gripperPitchServo";
    private static final String GRIPPER_GRIPPER_SERVO_VAR_NAME = "gripperGripperServo";

    private static final String[] DRIVE_VAR_NAMES = new String[] {FRONT_LEFT_DRIVE_VAR_NAME, FRONT_RIGHT_DRIVE_VAR_NAME, REAR_LEFT_DRIVE_VAR_NAME, REAR_RIGHT_DRIVE_VAR_NAME, LEFT_LIFT_DRIVE_VAR_NAME, RIGHT_LIFT_DRIVE_VAR_NAME};
    private static final String[] SERVO_VAR_NAMES = new String[] {LEFT_AUXILIARY_ARM_SERVO_VAR_NAME, RIGHT_AUXILIARY_ARM_SERVO_VAR_NAME, GRIPPER_PITCH_SERVO_VAR_NAME, GRIPPER_GRIPPER_SERVO_VAR_NAME};

    private static final byte MAX_PRIORITY = 127;
    private static final byte ELEVATED_PRIORITY = 64;
    private static final byte STANDARD_PRIORITY = 32;
    private static final byte LOW_PRIORITY = 16;
    private static final byte MIN_PRIORITY = 0;

    private final MecanumDriveI mecanumDrive;
    private final SliderArmI sliderArm;
    private final StatefulServoI leftArm;
    private final StatefulServoI rightArm;
    private final GripperI gripper;

    private final DistanceI leftDistance;
    private final DistanceI rightDistance;
    private final DistanceI frontDistance;
    private final DistanceI rearDistance;
    private final DistanceI liftHeight;
    private final ColorI color;

    private final IndexableStrip5V lights;

    private final DistanceI[] unitfulDevices;

    private final AtomicReference<PriorityQueue<Initializer>> refreshables;

    private AtomicBoolean mecanumDriveRefreshed = new AtomicBoolean(true);
    private AtomicBoolean sliderArmRefreshed = new AtomicBoolean(true);
    private AtomicBoolean leftArmAndRightArmRefreshed = new AtomicBoolean(true);
    private AtomicBoolean gripperRefreshed = new AtomicBoolean(true);
    private AtomicBoolean sensorsRefreshed = new AtomicBoolean(true);

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

        this.leftDistance = new Rev2mDistanceImpl(map, new DistanceDependencies().setName(LEFT_DISTANCE_NAME));
        this.rightDistance = new Rev2mDistanceImpl(map, new DistanceDependencies().setName(RIGHT_DISTANCE_NAME));
        this.liftHeight = new Rev2mDistanceImpl(map, new DistanceDependencies().setName(LIFT_HEIGHT_NAME));
        this.frontDistance = new Rev2mDistanceImpl(map, new DistanceDependencies().setName(FRONT_DISTANCE_NAME));
        this.rearDistance = new Rev2mDistanceImpl(map, new DistanceDependencies().setName(REAR_DISTANCE_NAME));
        this.color = new RevColorSensorV3Impl(map, new RevColorSensorV3Dependencies().setName(COLOR_NAME));
        this.lights = new IndexableStrip5VImpl(map, new IndexableStrip5VDependencies().setName(LIGHTS_NAME));

        unitfulDevices = new DistanceI[] { leftDistance, rightDistance, liftHeight, frontDistance, rearDistance, color };
        refreshables = new AtomicReference<>(new PriorityQueue<>());
        refreshables.get().add(new InitializerImpl(MAX_PRIORITY, () -> {
            mecanumDrive.refresh();
            mecanumDriveRefreshed.set(true);
        }));
        refreshables.get().add(new InitializerImpl(ELEVATED_PRIORITY, () -> {
            sliderArm.refresh();
            sliderArmRefreshed.set(true);
        }));
        refreshables.get().add(new InitializerImpl(STANDARD_PRIORITY, () -> {
            leftArm.refresh();
            rightArm.refresh();
            leftArmAndRightArmRefreshed.set(true);
        }));
        refreshables.get().add(new InitializerImpl(LOW_PRIORITY, () -> {
            gripper.refresh();
            gripperRefreshed.set(true);
        }));
        refreshables.get().add(new InitializerImpl(MIN_PRIORITY, () -> {
            leftDistance.refresh();
            rightDistance.refresh();
            liftHeight.refresh();
            frontDistance.refresh();
            rearDistance.refresh();
            color.refresh();
            lights.refresh();
            sensorsRefreshed.set(true);
        }));
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
    public MecanumDrivetrainI getDrivetrain() {
        return mecanumDrive.getDrivetrain();
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

    @Observable(key = "Left Auxiliary Servo Position")
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
    @Observable(key = "Right Auxiliary Servo Position")
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
    @Observable(key = "Pitch Servo Position")
    public double getPitchPosition() {
        return gripper.getPitchPosition();
    }

    @Override
    public void setPitchPosition(double position) {
        gripper.setPitchPosition(position);
    }

    @Override
    @Observable(key = "Gripper Servo Position")
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
    @Observable(key = "Lift Distance")
    public double getLiftHeight() {
        if (sensorsRefreshed.get()) {
            return liftHeight.getDistance();
        }
        return 0;
    }

    @Override
    @Observable(key = "Left Distance")
    public double getDistanceLeft() {
        if (sensorsRefreshed.get()) {
            return leftDistance.getDistance();
        }
        return 0;
    }

    @Override
    @Observable(key = "Right Distance")
    public double getDistanceRight() {
        if (sensorsRefreshed.get()) {
            return rightDistance.getDistance();
        }
        return 0;
    }

    @Override
    @Observable(key = "Front Distance")
    public double getDistanceFront() {
        if (sensorsRefreshed.get()) {
            return frontDistance.getDistance();
        }
        return 0;
    }

    @Override
    @Observable(key = "Rear Distance")
    public double getDistanceRear() {
        if (sensorsRefreshed.get()) {
            return rearDistance.getDistance();
        }
        return 0;
    }

    @Override
    @Observable(key = "Color Red")
    public double getRed() {
        if (sensorsRefreshed.get()) {
            return color.getRed();
        }
        return 0;
    }

    @Override
    @Observable(key = "Color Green")
    public double getGreen() {
        if (sensorsRefreshed.get()) {
            return color.getGreen();
        }
        return 0;
    }

    @Override
    @Observable(key = "Color Blue")
    public double getBlue() {
        if (sensorsRefreshed.get()) {
            return color.getBlue();
        }
        return 0;
    }

    @Override
    @Observable(key = "Color Red Normalized")
    public double getNormalizedRed() {
        if (sensorsRefreshed.get()) {
            return color.getNormalizedRed();
        }
        return 0;
    }

    @Override
    @Observable(key = "Color Green Normalized")
    public double getNormalizedGreen() {
        if (sensorsRefreshed.get()) {
            return color.getNormalizedGreen();
        }
        return 0;
    }

    @Override
    @Observable(key = "Color Blue Normalized")
    public double getNormalizedBlue() {
        if (sensorsRefreshed.get()) {
            return color.getNormalizedBlue();
        }
        return 0;
    }

    @Override
    @Observable(key = "Color Light")
    public double getLight() {
        if (sensorsRefreshed.get()) {
            return color.getLight();
        }
        return 0;
    }

    @Override
    @Observable(key = "Color Raw Light")
    public double getRawLight() {
        if (sensorsRefreshed.get()) {
            return color.getRawLight();
        }
        return 0;
    }

    @Override
    @Observable(key = "Color Light Normalized")
    public double getNormalizedLight() {
        if (sensorsRefreshed.get()) {
            return color.getNormalizedLight();
        }
        return 0;
    }

    @Override
    public DistanceUnit getUnit() {
        if (sensorsRefreshed.get()) {
            for (DistanceI unitfulDevice : unitfulDevices) {
                unitfulDevice.setUnit(unitfulDevices[0].getUnit());
            }
            return unitfulDevices[0].getUnit();
        }
        return null;
    }

    @Override
    public void setUnit(DistanceUnit unit) {
        if (sensorsRefreshed.get()) {
            for (DistanceI distance : unitfulDevices) {
                distance.setUnit(unit);
            }
        }
    }

    @Override
    @Observable(key = "Color Distance")
    public double getDistance() {
        if (sensorsRefreshed.get()) {
            return color.getDistance();
        }
        return 0;
    }

    @Override
    @Observable(key = "Distance Unit")
    public String getUnitName() {
        if (sensorsRefreshed.get()) {
            return unitfulDevices[0].getUnitName();
        }
        return null;
    }

    @Override
    @Observable(key = "Left Lift Drive Power")
    public double getLiftDriveAPower() {
        if (sliderArmRefreshed.get()) {
            return getLiftDrives()[0].getPower();
        }
        return 0;
    }

    @Override
    @Observable(key = "Right Lift Drive Power")
    public double getLiftDriveBPower() {
        if (sliderArmRefreshed.get()) {
            return getLiftDrives()[1].getPower();
        }
        return 0;
    }

    @Override
    @Observable(key = "Left Lift Drive Position")
    public int getLiftDriveAPosition() {
        if (sliderArmRefreshed.get()) {
            return getLiftDrives()[0].getCurrentPosition();
        }
        return 0;
    }

    @Override
    @Observable(key = "Right Lift Drive Position")
    public int getLiftDriveBPosition() {
        if (sliderArmRefreshed.get()) {
            return getLiftDrives()[1].getCurrentPosition();
        }
        return 0;
    }

    @Override
    @Observable(key = "Left Lift Drive Mode")
    public String getLiftDriveAMode() {
        if (sliderArmRefreshed.get()) {
            return getLiftDrives()[0].getMode().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Right Lift Drive Mode")
    public String getLiftDriveBMode() {
        if (sliderArmRefreshed.get()) {
            return getLiftDrives()[1].getMode().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Left Lift Drive Direction")
    public String getLiftDriveADirection() {
        if (sliderArmRefreshed.get()) {
            return getLiftDrives()[0].getDirection().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Right Lift Drive Direction")
    public String getLiftDriveBDirection() {
        if (sliderArmRefreshed.get()) {
            return getLiftDrives()[1].getDirection().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Pitch Servo Direction")
    public String getPitchDirection() {
        if (gripperRefreshed.get()) {
            return getPitch().getDirection().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Gripper Servo Direction")
    public String getGripperDirection() {
        if (gripperRefreshed.get()) {
            return getGripper().getDirection().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Left Auxiliary Servo Direction")
    public String getAuxiliaryLeftServoDirection() {
        if (leftArmAndRightArmRefreshed.get()) {
            return getLeftServoA().getDirection().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Right Auxiliary Servo Direction")
    public String getAuxiliaryRightServoDirection() {
        if (leftArmAndRightArmRefreshed.get()) {
            return getRightServoA().getDirection().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Front Left Drive Power")
    public double getFrontLeftDrivePower() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getFrontLeft().getMotor().getPower();
        }
        return 0;
    }

    @Override
    @Observable(key = "Front Right Drive Power")
    public double getFrontRightDrivePower() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getFrontRight().getMotor().getPower();
        }
        return 0;
    }

    @Override
    @Observable(key = "Rear Left Drive Power")
    public double getRearLeftDrivePower() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getRearLeft().getMotor().getPower();
        }
        return 0;
    }

    @Override
    @Observable(key = "Rear Right Drive Power")
    public double getRearRightDriverPower() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getRearRight().getMotor().getPower();
        }
        return 0;
    }

    @Override
    @Observable(key = "Front Left Drive Position")
    public int getFrontLeftDrivePosition() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getFrontLeft().getMotor().getCurrentPosition();
        }
        return 0;
    }

    @Override
    @Observable(key = "Front Right Drive Position")
    public int getFrontRightDrivePosition() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getFrontRight().getMotor().getCurrentPosition();
        }
        return 0;
    }

    @Override
    @Observable(key = "Rear Left Drive Position")
    public int getRearLeftDrivePosition() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getRearLeft().getMotor().getCurrentPosition();
        }
        return 0;
    }

    @Override
    @Observable(key = "Rear Right Drive Position")
    public int getRearRightDrivePosition() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getRearRight().getMotor().getCurrentPosition();
        }
        return 0;
    }

    @Override
    @Observable(key = "Front Left Drive Mode")
    public String getFrontLeftDriveMode() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getFrontLeft().getMotor().getMode().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Front Right Drive Mode")
    public String getFrontRightDriveMode() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getFrontRight().getMotor().getMode().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Rear Left Drive Mode")
    public String getRearLeftDriveMode() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getRearLeft().getMotor().getMode().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Rear Right Drive Mode")
    public String getRearRightDriveMode() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getRearRight().getMotor().getMode().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Front Left Drive Direction")
    public String getFrontLeftDriveDirection() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getFrontLeft().getMotor().getDirection().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Front Right Drive Direction")
    public String getFrontRightDriveDirection() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getFrontRight().getMotor().getDirection().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Rear Left Drive Direction")
    public String getRearLeftDriveDirection() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getRearLeft().getMotor().getDirection().name();
        }
        return null;
    }

    @Override
    @Observable(key = "Rear Right Drive Direction")
    public String getRearRightDriveDirection() {
        if (mecanumDriveRefreshed.get()) {
            return getDrivetrain().getRearRight().getMotor().getDirection().name();
        }
        return null;
    }

    @Override
    public RevBlinkinLedDriver.BlinkinPattern getPattern() {
        if (sensorsRefreshed.get()) {
            return lights.getPattern();
        }
        return null;
    }

    @Override
    public void setPattern(RevBlinkinLedDriver.BlinkinPattern pattern) {
        if (sensorsRefreshed.get()) {
            lights.setPattern(pattern);
        }
    }

    @Override
    @Observable(key = "Pattern Name")
    public String getPatternName() {
        if (sensorsRefreshed.get()) {
            return lights.getPattern().name();
        }
        return null;
    }

    @Override
    public Refreshable refresh() {
        mecanumDriveRefreshed.set(false);
        sliderArmRefreshed.set(false);
        leftArmAndRightArmRefreshed.set(false);
        gripperRefreshed.set(false);
        sensorsRefreshed.set(false);
        Thread th = new Thread(() -> {
            while (!refreshables.get().isEmpty()) {
                Initializer initializer = refreshables.get().poll();
                if (initializer != null) {
                    initializer.getInitializer().run();
                }
            }
        });
        th.start();
        return this;
    }
}