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
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.intakes.CompliantIntake;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.intakes.CompliantIntakeDependenciesI;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.intakes.CompliantIntakeI;
import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.lights.IndexableStrip5V;
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
import org.firstinspires.ftc.teamcode.control.robots.sensors.ColorI;
import org.firstinspires.ftc.teamcode.control.robots.sensors.DigitalI;
import org.firstinspires.ftc.teamcode.control.robots.sensors.DigitalImpl;
import org.firstinspires.ftc.teamcode.control.robots.sensors.DistanceI;
import org.firstinspires.ftc.teamcode.control.robots.sensors.Rev2mDistanceImpl;
import org.firstinspires.ftc.teamcode.control.robots.sensors.RevColorSensorV3Impl;
import org.firstinspires.ftc.teamcode.utils.ObjectPair;

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
Touch sensor:              Touch            Port: 1    Bus: Digital 01Hub: 2
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
    private static final String INTAKE_DRIVE_A_NAME = "INTAKEA";
    private static final String INTAKE_DRIVE_B_NAME = "INTAKEB";

    private static final String LEFT_AUXILIARY_ARM_SERVO_NAME = "AUXL";
    private static final String RIGHT_AUXILIARY_ARM_SERVO_NAME = "AUXR";

    private static final String GRIPPER_PITCH_SERVO_NAME = "PITCH";
    private static final String GRIPPER_GRIPPER_SERVO_NAME = "GRIPPER";

    private static final String INTAKE_PITCH_SERVO_A_NAME = "PITCHA";
    private static final String INTAKE_PITCH_SERVO_B_NAME = "PITCHB";
    private static final String INTAKE_LEFT_SERVO_NAME = "INTAKE_LEFT";
    private static final String INTAKE_RIGHT_SERVO_NAME = "INTAKE_RIGHT";

    private static final String LEFT_DISTANCE_NAME = "DistanceLeft";
    private static final String RIGHT_DISTANCE_NAME = "DistanceRight";
    private static final String FRONT_DISTANCE_NAME = "DistanceFront";
    private static final String REAR_DISTANCE_NAME = "DistanceRear";
    private static final String LIFT_HEIGHT_NAME = "LiftHeight";

    private static final String COLOR_NAME = "Color";

    private static final String TOUCH_NAME = "Touch";

    private static final String LIGHTS_NAME = "LETTHEREBELIGHT";

    private static final String CAMERA_NAME = "Cam";

    private static final String[] DRIVE_NAMES = new String[] {FRONT_LEFT_DRIVE_NAME, FRONT_RIGHT_DRIVE_NAME, REAR_LEFT_DRIVE_NAME, REAR_RIGHT_DRIVE_NAME, LEFT_LIFT_DRIVE_NAME, RIGHT_LIFT_DRIVE_NAME, INTAKE_DRIVE_A_NAME, INTAKE_DRIVE_B_NAME};
    private static final String[] SERVO_NAMES = new String[] {LEFT_AUXILIARY_ARM_SERVO_NAME, RIGHT_AUXILIARY_ARM_SERVO_NAME, GRIPPER_PITCH_SERVO_NAME, GRIPPER_GRIPPER_SERVO_NAME, INTAKE_PITCH_SERVO_A_NAME, INTAKE_PITCH_SERVO_B_NAME, INTAKE_LEFT_SERVO_NAME, INTAKE_RIGHT_SERVO_NAME};

    private static final String FRONT_LEFT_DRIVE_VAR_NAME = "frontLeftDrive";
    private static final String FRONT_RIGHT_DRIVE_VAR_NAME = "frontRightDrive";
    private static final String REAR_LEFT_DRIVE_VAR_NAME = "rearLeftDrive";
    private static final String REAR_RIGHT_DRIVE_VAR_NAME = "rearRightDrive";
    private static final String LEFT_LIFT_DRIVE_VAR_NAME = "leftLiftDrive";
    private static final String RIGHT_LIFT_DRIVE_VAR_NAME = "rightLiftDrive";
    private static final String INTAKE_DRIVE_A_VAR_NAME = "intakeDriveA";
    private static final String INTAKE_DRIVE_B_VAR_NAME = "intakeDriveB";

    private static final String LEFT_AUXILIARY_ARM_SERVO_VAR_NAME = "leftAuxiliaryArmServo";
    private static final String RIGHT_AUXILIARY_ARM_SERVO_VAR_NAME = "rightAuxiliaryArmServo";

    private static final String GRIPPER_PITCH_SERVO_VAR_NAME = "gripperPitchServo";
    private static final String GRIPPER_GRIPPER_SERVO_VAR_NAME = "gripperGripperServo";
    private static final String INTAKE_PITCH_SERVO_A_VAR_NAME = "intakePitchServoA";
    private static final String INTAKE_PITCH_SERVO_B_VAR_NAME = "intakePitchServoB";
    private static final String INTAKE_LEFT_SERVO_VAR_NAME = "intakeLeftServo";
    private static final String INTAKE_RIGHT_SERVO_VAR_NAME = "intakeRightServo";

    private static final String[] DRIVE_VAR_NAMES = new String[] {FRONT_LEFT_DRIVE_VAR_NAME, FRONT_RIGHT_DRIVE_VAR_NAME, REAR_LEFT_DRIVE_VAR_NAME, REAR_RIGHT_DRIVE_VAR_NAME, LEFT_LIFT_DRIVE_VAR_NAME, RIGHT_LIFT_DRIVE_VAR_NAME, INTAKE_DRIVE_A_VAR_NAME, INTAKE_DRIVE_B_VAR_NAME};
    private static final String[] SERVO_VAR_NAMES = new String[] {LEFT_AUXILIARY_ARM_SERVO_VAR_NAME, RIGHT_AUXILIARY_ARM_SERVO_VAR_NAME, GRIPPER_PITCH_SERVO_VAR_NAME, GRIPPER_GRIPPER_SERVO_VAR_NAME, INTAKE_PITCH_SERVO_A_VAR_NAME, INTAKE_PITCH_SERVO_B_VAR_NAME, INTAKE_LEFT_SERVO_VAR_NAME, INTAKE_RIGHT_SERVO_VAR_NAME};

    private final MecanumDriveI mecanumDrive;
    private final SliderArmI sliderArm;
    private final StatefulServoI leftArm;
    private final StatefulServoI rightArm;
    private final GripperI gripper;
    private final CompliantIntakeI intake;

    private final DistanceI leftDistance;
    private final DistanceI rightDistance;
    private final DistanceI frontDistance;
    private final DistanceI rearDistance;
    private final DistanceI liftHeight;
    private final ColorI color;

    private final DigitalI touch;

    private final IndexableStrip5V lights;

    private final DistanceI[] unitfulDevices;

    public static CompetitionRobot instantiate(HardwareMap map) {
        return new CompetitionRobot(new MecanumDrive(map), new SliderArm(map, new SliderArmDependencies().setSliderDriveNames(LEFT_LIFT_DRIVE_NAME, RIGHT_LIFT_DRIVE_NAME).setSliderDriveDirections(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD)), new StatefulServo(map, new StatefulServoDependencies().setServoName(LEFT_AUXILIARY_ARM_SERVO_NAME)), new StatefulServo(map, new StatefulServoDependencies().setServoName(RIGHT_AUXILIARY_ARM_SERVO_NAME)), new Gripper(map), new CompliantIntake(map), map);
    }

    public static CompetitionRobot instantiateWithDependencies(HardwareMap map, MecanumDependenciesI mecanumDependencies, SliderArmDependenciesI sliderArmDependencies, StatefulServoDependenciesI leftArmDependencies, StatefulServoDependenciesI rightArmDependencies, GripperDependenciesI gripperDependencies, CompliantIntakeDependenciesI intakeDependencies) {
        return new CompetitionRobot(new MecanumDrive(map, mecanumDependencies), new SliderArm(map, sliderArmDependencies), new StatefulServo(map, leftArmDependencies), new StatefulServo(map, rightArmDependencies), new Gripper(map, gripperDependencies), new CompliantIntake(map, intakeDependencies), map);
    }

    private CompetitionRobot(MecanumDriveI mecanumDrive, SliderArmI sliderArm, StatefulServoI leftArm, StatefulServoI rightArm, GripperI gripper, CompliantIntakeI intake, HardwareMap map) {
        this.mecanumDrive = mecanumDrive;
        this.sliderArm = sliderArm;
        this.leftArm = leftArm;
        this.rightArm = rightArm;
        this.gripper = gripper;
        this.intake = intake;

        this.leftDistance = new Rev2mDistanceImpl(LEFT_DISTANCE_NAME, map);
        this.rightDistance = new Rev2mDistanceImpl(RIGHT_DISTANCE_NAME, map);
        this.liftHeight = new Rev2mDistanceImpl(LIFT_HEIGHT_NAME, map);
        this.frontDistance = new Rev2mDistanceImpl(FRONT_DISTANCE_NAME, map);
        this.rearDistance = new Rev2mDistanceImpl(REAR_DISTANCE_NAME, map);
        this.color = new RevColorSensorV3Impl(COLOR_NAME, map);
        this.touch = new DigitalImpl(TOUCH_NAME, map);
        this.lights = new IndexableStrip5VImpl(LIGHTS_NAME, map);

        unitfulDevices = new DistanceI[] { leftDistance, rightDistance, liftHeight, frontDistance, rearDistance, color };
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
        return liftHeight.getDistance();
    }

    @Override
    @Observable(key = "Left Distance")
    public double getDistanceLeft() {
        return leftDistance.getDistance();
    }

    @Override
    @Observable(key = "Right Distance")
    public double getDistanceRight() {
        return rightDistance.getDistance();
    }

    @Override
    @Observable(key = "Front Distance")
    public double getDistanceFront() {
        return frontDistance.getDistance();
    }

    @Override
    @Observable(key = "Rear Distance")
    public double getDistanceRear() {
        return rearDistance.getDistance();
    }

    @Override
    @Observable(key = "Touch Pressed")
    public boolean isTouchPressed() {
        return touch.getHigh();
    }

    @Override
    @Observable(key = "Color Red")
    public double getRed() {
        return color.getRed();
    }

    @Override
    @Observable(key = "Color Green")
    public double getGreen() {
        return color.getGreen();
    }

    @Override
    @Observable(key = "Color Blue")
    public double getBlue() {
        return color.getBlue();
    }

    @Override
    @Observable(key = "Color Red Normalized")
    public double getNormalizedRed() {
        return color.getNormalizedRed();
    }

    @Override
    @Observable(key = "Color Green Normalized")
    public double getNormalizedGreen() {
        return color.getNormalizedGreen();
    }

    @Override
    @Observable(key = "Color Blue Normalized")
    public double getNormalizedBlue() {
        return color.getNormalizedBlue();
    }

    @Override
    @Observable(key = "Color Light")
    public double getLight() {
        return color.getLight();
    }

    @Override
    @Observable(key = "Color Raw Light")
    public double getRawLight() {
        return color.getRawLight();
    }

    @Override
    @Observable(key = "Color Light Normalized")
    public double getNormalizedLight() {
        return color.getNormalizedLight();
    }

    @Override
    public DistanceUnit getUnit() {
        for (DistanceI unitfulDevice : unitfulDevices) {
            unitfulDevice.setUnit(unitfulDevices[0].getUnit());
        }
        return unitfulDevices[0].getUnit();
    }

    @Override
    public void setUnit(DistanceUnit unit) {
        for (DistanceI distance : unitfulDevices) {
            distance.setUnit(unit);
        }
    }

    @Override
    @Observable(key = "Color Distance")
    public double getDistance() {
        return color.getDistance();
    }

    @Override
    @Observable(key = "Distance Unit")
    public String getUnitName() {
        return unitfulDevices[0].getUnitName();
    }

    @Override
    public ObjectPair<Double, Double> getIntakePitchPositions() {
        return intake.getIntakePitchPositions();
    }

    @Override
    public void setIntakePitchPositions(double position) {
        intake.setIntakePitchPositions(position);
    }

    @Override
    @Observable(key = "Intake Left Servo Position")
    public double getLeftPosition() {
        return intake.getLeftPosition();
    }

    @Override
    public void setLeftPosition(double position) {
        intake.setLeftPosition(position);
    }

    @Override
    @Observable(key = "Intake Right Servo Position")
    public double getRightPosition() {
        return intake.getRightPosition();
    }

    @Override
    public void setRightPosition(double position) {
        intake.setRightPosition(position);
    }

    @Override
    public ObjectPair<Double, Double> getIntakeSpeeds() {
        return intake.getIntakeSpeeds();
    }

    @Override
    public void setIntakeSpeeds(double speed) {
        intake.setIntakeSpeeds(speed);
    }

    @Override
    public ObjectPair<Servo, Servo> getIntakePitch() {
        return intake.getIntakePitch();
    }

    @Override
    public Servo getLeft() {
        return intake.getLeft();
    }

    @Override
    public Servo getRight() {
        return intake.getRight();
    }

    @Override
    public ObjectPair<DcMotor, DcMotor> getIntake() {
        return intake.getIntake();
    }

    @Override
    @Observable(key = "Left Lift Drive Power")
    public double getLiftDriveAPower() {
        return getLiftDrives()[0].getPower();
    }

    @Override
    @Observable(key = "Right Lift Drive Power")
    public double getLiftDriveBPower() {
        return getLiftDrives()[1].getPower();
    }

    @Override
    @Observable(key = "Left Lift Drive Position")
    public int getLiftDriveAPosition() {
        return getLiftDrives()[0].getCurrentPosition();
    }

    @Override
    @Observable(key = "Right Lift Drive Position")
    public int getLiftDriveBPosition() {
        return getLiftDrives()[1].getCurrentPosition();
    }

    @Override
    @Observable(key = "Left Lift Drive Mode")
    public String getLiftDriveAMode() {
        return getLiftDrives()[0].getMode().name();
    }

    @Override
    @Observable(key = "Right Lift Drive Mode")
    public String getLiftDriveBMode() {
        return getLiftDrives()[1].getMode().name();
    }

    @Override
    @Observable(key = "Left Lift Drive Direction")
    public String getLiftDriveADirection() {
        return getLiftDrives()[0].getDirection().name();
    }

    @Override
    @Observable(key = "Right Lift Drive Direction")
    public String getLiftDriveBDirection() {
        return getLiftDrives()[1].getDirection().name();
    }

    @Override
    @Observable(key = "Pitch Servo Direction")
    public String getPitchDirection() {
        return getPitch().getDirection().name();
    }

    @Override
    @Observable(key = "Gripper Servo Direction")
    public String getGripperDirection() {
        return getGripper().getDirection().name();
    }

    @Override
    @Observable(key = "Intake Drive A Power")
    public double getIntakeDriveAPower() {
        return getIntake().getA().getPower();
    }

    @Override
    @Observable(key = "Intake Drive B Power")
    public double getIntakeDriveBPower() {
        return getIntake().getB().getPower();
    }

    @Override
    @Observable(key = "Intake Drive A Position")
    public int getIntakeDriveAPosition() {
        return getIntake().getA().getCurrentPosition();
    }

    @Override
    @Observable(key = "Intake Drive B Position")
    public int getIntakeDriveBPosition() {
        return getIntake().getB().getCurrentPosition();
    }

    @Override
    @Observable(key = "Intake Drive A Mode")
    public String getIntakeDriveAMode() {
        return getIntake().getA().getMode().name();
    }

    @Override
    @Observable(key = "Intake Drive B Mode")
    public String getIntakeDriveBMode() {
        return getIntake().getB().getMode().name();
    }

    @Override
    @Observable(key = "Intake Drive A Direction")
    public String getIntakeDriveADirection() {
        return getIntake().getA().getDirection().name();
    }

    @Override
    @Observable(key = "Intake Drive B Direction")
    public String getIntakeDriveBDirection() {
        return getIntake().getB().getDirection().name();
    }

    @Override
    @Observable(key = "Intake Pitch Servo A Position")
    public double getIntakePitchAPosition() {
        return getIntakePitch().getA().getPosition();
    }

    @Override
    @Observable(key = "Intake Pitch Servo B Position")
    public double getIntakePitchBPosition() {
        return getIntakePitch().getB().getPosition();
    }

    @Override
    @Observable(key = "Intake Pitch Servo A Direction")
    public String getIntakePitchADirection() {
        return getIntakePitch().getA().getDirection().name();
    }

    @Override
    @Observable(key = "Intake Pitch Servo B Direction")
    public String getIntakePitchBDirection() {
        return getIntakePitch().getB().getDirection().name();
    }

    @Override
    @Observable(key = "Intake Left Servo Direction")
    public String getIntakeLeftDirection() {
        return intake.getLeft().getDirection().name();
    }

    @Override
    @Observable(key = "Intake Right Servo Direction")
    public String getIntakeRightDirection() {
        return intake.getRight().getDirection().name();
    }

    @Override
    @Observable(key = "Left Auxiliary Servo Direction")
    public String getAuxiliaryLeftServoDirection() {
        return getLeftServoA().getDirection().name();
    }

    @Override
    @Observable(key = "Right Auxiliary Servo Direction")
    public String getAuxiliaryRightServoDirection() {
        return getRightServoA().getDirection().name();
    }

    @Override
    @Observable(key = "Front Left Drive Power")
    public double getFrontLeftDrivePower() {
        return getDrivetrain().getFrontLeft().getMotor().getPower();
    }

    @Override
    @Observable(key = "Front Right Drive Power")
    public double getFrontRightDrivePower() {
        return getDrivetrain().getFrontRight().getMotor().getPower();
    }

    @Override
    @Observable(key = "Rear Left Drive Power")
    public double getRearLeftDrivePower() {
        return getDrivetrain().getRearLeft().getMotor().getPower();
    }

    @Override
    @Observable(key = "Rear Right Drive Power")
    public double getRearRightDriverPower() {
        return getDrivetrain().getRearRight().getMotor().getPower();
    }

    @Override
    @Observable(key = "Front Left Drive Position")
    public int getFrontLeftDrivePosition() {
        return getDrivetrain().getFrontLeft().getMotor().getCurrentPosition();
    }

    @Override
    @Observable(key = "Front Right Drive Position")
    public int getFrontRightDrivePosition() {
        return getDrivetrain().getFrontRight().getMotor().getCurrentPosition();
    }

    @Override
    @Observable(key = "Rear Left Drive Position")
    public int getRearLeftDrivePosition() {
        return getDrivetrain().getRearLeft().getMotor().getCurrentPosition();
    }

    @Override
    @Observable(key = "Rear Right Drive Position")
    public int getRearRightDrivePosition() {
        return getDrivetrain().getRearRight().getMotor().getCurrentPosition();
    }

    @Override
    @Observable(key = "Front Left Drive Mode")
    public String getFrontLeftDriveMode() {
        return getDrivetrain().getFrontLeft().getMotor().getMode().name();
    }

    @Override
    @Observable(key = "Front Right Drive Mode")
    public String getFrontRightDriveMode() {
        return getDrivetrain().getFrontRight().getMotor().getMode().name();
    }

    @Override
    @Observable(key = "Rear Left Drive Mode")
    public String getRearLeftDriveMode() {
        return getDrivetrain().getRearLeft().getMotor().getMode().name();
    }

    @Override
    @Observable(key = "Rear Right Drive Mode")
    public String getRearRightDriveMode() {
        return getDrivetrain().getRearRight().getMotor().getMode().name();
    }

    @Override
    @Observable(key = "Front Left Drive Direction")
    public String getFrontLeftDriveDirection() {
        return getDrivetrain().getFrontLeft().getMotor().getDirection().name();
    }

    @Override
    @Observable(key = "Front Right Drive Direction")
    public String getFrontRightDriveDirection() {
        return getDrivetrain().getFrontRight().getMotor().getDirection().name();
    }

    @Override
    @Observable(key = "Rear Left Drive Direction")
    public String getRearLeftDriveDirection() {
        return getDrivetrain().getRearLeft().getMotor().getDirection().name();
    }

    @Override
    @Observable(key = "Rear Right Drive Direction")
    public String getRearRightDriveDirection() {
        return getDrivetrain().getRearRight().getMotor().getDirection().name();
    }

    @Override
    public RevBlinkinLedDriver.BlinkinPattern getPattern() {
        return lights.getPattern();
    }

    @Override
    public void setPattern(RevBlinkinLedDriver.BlinkinPattern pattern) {
        lights.setPattern(pattern);
    }

    @Override
    @Observable(key = "Pattern Name")
    public String getPatternName() {
        return lights.getPattern().name();
    }
}