package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobotI;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDriveInput;
import org.firstinspires.ftc.teamcode.utils.AutonomousFileGenerator;
import org.firstinspires.ftc.teamcode.utils.CommonOptimizations;
import org.firstinspires.ftc.teamcode.utils.Recordable;
import org.firstinspires.ftc.teamcode.utils.Recorder;
import org.firstinspires.ftc.teamcode.utils.RecorderI;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Function;

@TeleOp(name="CompetitionRobot")
public class CompetitionTeleOp extends OpMode implements Recordable {
    private static final int TELEMETRY_TRANSMISSION_INTERVAL    = 75;
    private static final String GAMEPAD_1_VALUES                = "Gamepad 1 Values";
    private static final String GAMEPAD_2_VALUES                = "Gamepad 2 Values";

    private static final String LEFT_TRIGGER                    = "Left Trigger";
    private static final String RIGHT_TRIGGER                   = "Right Trigger";
    private static final String LEFT_BUMPER                     = "Left Bumper";
    private static final String RIGHT_BUMPER                    = "Right Bumper";
    private static final String LEFT_STICK_X                    = "Left Stick X";
    private static final String LEFT_STICK_Y                    = "Left Stick Y";
    private static final String RIGHT_STICK_X                   = "Right Stick X";
    private static final String RIGHT_STICK_Y                   = "Right Stick Y";
    private static final String BUTTON_A                        = "A";
    private static final String BUTTON_B                        = "B";
    private static final String BUTTON_X                        = "X";
    private static final String BUTTON_Y                        = "Y";
    private static final String DPAD_DOWN                       = "Dpad Down";
    private static final String DPAD_LEFT                       = "Dpad Left";
    private static final String DPAD_UP                         = "Dpad Up";
    private static final String DPAD_RIGHT                      = "Dpad Right";

    private static final String VARIABLE_VALUES                 = "Variable Values";

    private static final String PITCH_POSITION                  = "Pitch Position";
    private static final String PITCH_TOGGLED_OUT               = "Pitch Toggled Out";
    private static final String INTAKE_POSITION                 = "Intake Position";
    private static final String INTAKE_TOGGLED_OUT              = "Intake Toggled Out";
    private static final String GRIPPER_POSITION                = "Gripper Position";
    private static final String GRIPPER_TOGGLED_CLOSED          = "Gripper Toggled Closed";
    private static final String FOUNDATION_ARMS_TOGGLED_OUT     = "Foundation Arms Toggled Out";
    private static final String DRIVE_SPEED_CAP                 = "Drive Speed Cap";
    private static final String DIAGNOSTIC_MODE_TOGGLED         = "Diagnostic Mode Toggled";
    private static final String GAMEPAD_1_RB_PRESSED            = "Gamepad 1 RB Pressed";
    private static final String GAMEPAD_1_X_PRESSED             = "Gamepad 1 X pressed";
    private static final String GAMEPAD_2_LB_PRESSED            = "Gamepad 2 LB Pressed";
    private static final String GAMEPAD_2_RB_PRESSED            = "Gamepad 2 RB Pressed";
    private static final String GAMEPAD_2_A_PRESSED             = "Gamepad 2 A Pressed";
    private static final String SAVING                          = "Saving";

    private static final String MOTOR_VALUES                    = "Motor Values";

    private static final String FRONT_LEFT_DRIVE_POWER          = "Front Left Drive Power";
    private static final String FRONT_RIGHT_DRIVE_POWER         = "Front Right Drive Power";
    private static final String REAR_LEFT_DRIVE_POWER           = "Rear Left Drive Power";
    private static final String REAR_RIGHT_DRIVE_POWER          = "Rear Right Drive Power";
    private static final String LEFT_LIFT_DRIVE_POWER           = "Left Lift Drive Power";
    private static final String RIGHT_LIFT_DRIVE_POWER          = "Right Lift Drive Power";

    private static final String FRONT_LEFT_DRIVE_POSITION       = "Front Left Drive Position";
    private static final String FRONT_RIGHT_DRIVE_POSITION      = "Front Right Drive Position";
    private static final String REAR_LEFT_DRIVE_POSITION        = "Rear Left Drive Position";
    private static final String REAR_RIGHT_DRIVE_POSITION       = "Rear Right Drive Position";
    private static final String LEFT_LIFT_DRIVE_POSITION        = "Left Lift Drive Position";
    private static final String RIGHT_LIFT_DRIVE_POSITION       = "Right Lift Drive Position";

    private static final String FRONT_LEFT_DRIVE_MODE           = "Front Left Drive Mode";
    private static final String FRONT_RIGHT_DRIVE_MODE          = "Front Right Drive Mode";
    private static final String REAR_LEFT_DRIVE_MODE            = "Rear Left Drive Mode";
    private static final String REAR_RIGHT_DRIVE_MODE           = "Rear Right Drive Mode";
    private static final String LEFT_LIFT_DRIVE_MODE            = "Left Lift Drive Mode";
    private static final String RIGHT_LIFT_DRIVE_MODE           = "Right Lift Drive Mode";

    private static final String FRONT_LEFT_DRIVE_DIRECTION      = "Front Left Drive Direction";
    private static final String FRONT_RIGHT_DRIVE_DIRECTION     = "Front Right Drive Direction";
    private static final String REAR_LEFT_DRIVE_DIRECTION       = "Rear Left Drive Direction";
    private static final String REAR_RIGHT_DRIVE_DIRECTION      = "Rear Right Drive Direction";
    private static final String LEFT_LIFT_DRIVE_DIRECTION       = "Left Lift Drive Direction";
    private static final String RIGHT_LIFT_DRIVE_DIRECTION      = "Right Lift Drive Direction";

    private static final String SERVO_VALUES                    = "Servo Values";

    private static final String LEFT_AUXILIARY_SERVO_POSITION   = "Left Auxiliary Servo Position";
    private static final String RIGHT_AUXILIARY_SERVO_POSITION  = "Right Auxiliary Servo Position";
    private static final String PITCH_SERVO_POSITION            = "Pitch Servo Position";
    private static final String INTAKE_SERVO_POSITION           = "Intake Servo Position";
    private static final String GRIPPER_SERVO_POSITION          = "Gripper Servo Position";

    private static final String LEFT_AUXILIARY_SERVO_DIRECTION  = "Left Auxiliary Servo Direction";
    private static final String RIGHT_AUXILIARY_SERVO_DIRECTION = "Right Auxiliary Servo Direction";
    private static final String PITCH_SERVO_DIRECTION           = "Pitch Servo Direction";
    private static final String INTAKE_SERVO_DIRECTION          = "Intake Servo Direction";
    private static final String GRIPPER_SERVO_DIRECTION         = "Gripper Servo Direction";

    private static final String SENSOR_VALUES                   = "Sensor Values";

    private static final String LEFT_DISTANCE                   = "Left Distance";
    private static final String RIGHT_DISTANCE                  = "Right Distance";
    private static final String LIFT_DISTANCE                   = "Lift Distance";
    private static final String LEFT_DISTANCE_UNIT              = "Left Distance Unit";
    private static final String RIGHT_DISTANCE_UNIT             = "Right Distance Unit";
    private static final String LIFT_DISTANCE_UNIT              = "Lift Distance Unit";

    private static final String COLOR_DISTANCE                  = "Color Distance";
    private static final String COLOR_DISTANCE_UNIT             = "Color Distance Unit";
    private static final String COLOR_RED                       = "Color Red";
    private static final String COLOR_GREEN                     = "Color Green";
    private static final String COLOR_BLUE                      = "Color Blue";
    private static final String COLOR_RED_NORMALIZED            = "Color Red Normalized";
    private static final String COLOR_GREEN_NORMALIZED          = "Color Green Normalized";
    private static final String COLOR_BLUE_NORMALIZED           = "Color Blue Normalized";
    private static final String COLOR_LIGHT                     = "Color Light";
    private static final String COLOR_RAW_LIGHT                 = "Color Raw Light";
    private static final String COLOR_LIGHT_NORMALIZED          = "Color Light Normalized";



    private static final double MIN_TRIGGER_VALUE = 0x0;

    private static final double DRIVE_SPEED_CAPACITY = .8;

    private static final byte LOW_SPEED_CONTROL_FACTOR = 0x3; // This is exponential ie stickValue^LOW_SPEED_CONTROL_FACTOR

    private static final double DEFAULT_PITCH_POSITION = -0.05;
    private static final double DEFAULT_INTAKE_POSITION = 0.1;
    private static final byte DEFAULT_GRIPPER_POSITION = 0x0;

    private static final byte PITCH_MIN = -0x1;

    private static final byte ARTIFICIAL_SERVO_MIN = -0x1;
    private static final byte ARTIFICIAL_SERVO_MAX = 0x1;

    private static final byte ACTUAL_SERVO_MIN = 0x0;
    private static final byte ACTUAL_SERVO_MAX = 0x1;

    private static final double DEFAULT_VELOCITY_CHANGE = 0;

    private static final byte RECORDING_THREAD_PRIORITY = 0x9;

    private static final Function<LinkedList<String>, LinkedList<String>> powerOptimizer = (LinkedList<String> statements) -> new LinkedList<>(new CommonOptimizations().optimizeSleepCalls(statements));

    private CompetitionRobotI robot;
    private Runnable onStateChanged = () -> {
    };

    private String[] motorNames;
    private String[] servoNames;
    private String[] motorVarNames;
    private String[] servoVarNames;

    private RecorderI recorderByPower;

    private double pitchPosition = PITCH_MIN;
    private boolean pitchToggledOut = false;
    private double intakePosition = DEFAULT_INTAKE_POSITION;
    private boolean intakeToggledOut = false;
    private double gripperPosition = DEFAULT_GRIPPER_POSITION;
    private boolean gripperToggledClosed = false;

    private boolean foundationArmsToggledOut = false;

    private double driveSpeedCap = DRIVE_SPEED_CAPACITY;

    private boolean diagnosticModeToggled = false;

    private boolean gamepad1RBPressed;
    private boolean gamepad1XPressed;
    private boolean gamepad2LBPressed;
    private boolean gamepad2RBPressed;
    private boolean gamepad2APressed;

    private boolean saving = false;
    
    private HashMap<String, Telemetry.Item> telemetryItems = new HashMap<>();

    private void initTelemetryData() {
        telemetry.addLine(GAMEPAD_1_VALUES);
        telemetryItems.put(GAMEPAD_1_VALUES + LEFT_TRIGGER, telemetry.addData(LEFT_TRIGGER, gamepad1.left_trigger));
        telemetryItems.put(GAMEPAD_1_VALUES + RIGHT_TRIGGER, telemetry.addData(RIGHT_TRIGGER, gamepad1.right_trigger));
        telemetryItems.put(GAMEPAD_1_VALUES + LEFT_BUMPER, telemetry.addData(LEFT_BUMPER, gamepad1.left_bumper));
        telemetryItems.put(GAMEPAD_1_VALUES + RIGHT_BUMPER, telemetry.addData(RIGHT_BUMPER, gamepad1.right_bumper));
        telemetryItems.put(GAMEPAD_1_VALUES + LEFT_STICK_X, telemetry.addData(LEFT_STICK_X, gamepad1.left_stick_x));
        telemetryItems.put(GAMEPAD_1_VALUES + LEFT_STICK_Y, telemetry.addData(LEFT_STICK_Y, gamepad1.left_stick_y));
        telemetryItems.put(GAMEPAD_1_VALUES + RIGHT_STICK_X, telemetry.addData(RIGHT_STICK_X, gamepad1.right_stick_x));
        telemetryItems.put(GAMEPAD_1_VALUES + RIGHT_STICK_Y, telemetry.addData(RIGHT_STICK_Y, gamepad1.right_stick_y));
        telemetryItems.put(GAMEPAD_1_VALUES + BUTTON_A, telemetry.addData(BUTTON_A, gamepad1.a));
        telemetryItems.put(GAMEPAD_1_VALUES + BUTTON_B, telemetry.addData(BUTTON_B, gamepad1.b));
        telemetryItems.put(GAMEPAD_1_VALUES + BUTTON_X, telemetry.addData(BUTTON_X, gamepad1.x));
        telemetryItems.put(GAMEPAD_1_VALUES + BUTTON_Y, telemetry.addData(BUTTON_Y, gamepad1.y));
        telemetryItems.put(GAMEPAD_1_VALUES + DPAD_DOWN, telemetry.addData(DPAD_DOWN, gamepad1.dpad_down));
        telemetryItems.put(GAMEPAD_1_VALUES + DPAD_LEFT, telemetry.addData(DPAD_LEFT, gamepad1.dpad_left));
        telemetryItems.put(GAMEPAD_1_VALUES + DPAD_UP, telemetry.addData(DPAD_UP, gamepad1.dpad_up));
        telemetryItems.put(GAMEPAD_1_VALUES + DPAD_RIGHT, telemetry.addData(DPAD_RIGHT, gamepad1.dpad_right));
        telemetry.addLine();

        telemetry.addLine(GAMEPAD_2_VALUES);
        telemetryItems.put(GAMEPAD_2_VALUES + LEFT_TRIGGER, telemetry.addData(LEFT_TRIGGER, gamepad2.left_trigger));
        telemetryItems.put(GAMEPAD_2_VALUES + RIGHT_TRIGGER, telemetry.addData(RIGHT_TRIGGER, gamepad2.right_trigger));
        telemetryItems.put(GAMEPAD_2_VALUES + LEFT_BUMPER, telemetry.addData(LEFT_BUMPER, gamepad2.left_bumper));
        telemetryItems.put(GAMEPAD_2_VALUES + RIGHT_BUMPER, telemetry.addData(RIGHT_BUMPER, gamepad2.right_bumper));
        telemetryItems.put(GAMEPAD_2_VALUES + LEFT_STICK_X, telemetry.addData(LEFT_STICK_X, gamepad2.left_stick_x));
        telemetryItems.put(GAMEPAD_2_VALUES + LEFT_STICK_Y, telemetry.addData(LEFT_STICK_Y, gamepad2.left_stick_y));
        telemetryItems.put(GAMEPAD_2_VALUES + RIGHT_STICK_X, telemetry.addData(RIGHT_STICK_X, gamepad2.right_stick_x));
        telemetryItems.put(GAMEPAD_2_VALUES + RIGHT_STICK_Y, telemetry.addData(RIGHT_STICK_Y, gamepad2.right_stick_y));
        telemetryItems.put(GAMEPAD_2_VALUES + BUTTON_A, telemetry.addData(BUTTON_A, gamepad2.a));
        telemetryItems.put(GAMEPAD_2_VALUES + BUTTON_B, telemetry.addData(BUTTON_B, gamepad2.b));
        telemetryItems.put(GAMEPAD_2_VALUES + BUTTON_X, telemetry.addData(BUTTON_X, gamepad2.x));
        telemetryItems.put(GAMEPAD_2_VALUES + BUTTON_Y, telemetry.addData(BUTTON_Y, gamepad2.y));
        telemetryItems.put(GAMEPAD_2_VALUES + DPAD_DOWN, telemetry.addData(DPAD_DOWN, gamepad2.dpad_down));
        telemetryItems.put(GAMEPAD_2_VALUES + DPAD_LEFT, telemetry.addData(DPAD_LEFT, gamepad2.dpad_left));
        telemetryItems.put(GAMEPAD_2_VALUES + DPAD_UP, telemetry.addData(DPAD_UP, gamepad2.dpad_up));
        telemetryItems.put(GAMEPAD_2_VALUES + DPAD_RIGHT, telemetry.addData(DPAD_RIGHT, gamepad2.dpad_right));
        telemetry.addLine();

        telemetry.addLine(VARIABLE_VALUES);
        telemetryItems.put(PITCH_POSITION, telemetry.addData(PITCH_POSITION, pitchPosition));
        telemetryItems.put(PITCH_TOGGLED_OUT, telemetry.addData(PITCH_TOGGLED_OUT, pitchToggledOut));
        telemetryItems.put(INTAKE_POSITION, telemetry.addData(INTAKE_POSITION, intakePosition));
        telemetryItems.put(INTAKE_TOGGLED_OUT, telemetry.addData(INTAKE_TOGGLED_OUT, intakeToggledOut));
        telemetryItems.put(GRIPPER_POSITION, telemetry.addData(GRIPPER_POSITION, gripperPosition));
        telemetryItems.put(GRIPPER_TOGGLED_CLOSED, telemetry.addData(GRIPPER_TOGGLED_CLOSED, gripperToggledClosed));
        telemetryItems.put(FOUNDATION_ARMS_TOGGLED_OUT, telemetry.addData(FOUNDATION_ARMS_TOGGLED_OUT, foundationArmsToggledOut));
        telemetryItems.put(DRIVE_SPEED_CAP, telemetry.addData(DRIVE_SPEED_CAP, driveSpeedCap));
        telemetryItems.put(DIAGNOSTIC_MODE_TOGGLED, telemetry.addData(DIAGNOSTIC_MODE_TOGGLED, diagnosticModeToggled));
        telemetryItems.put(GAMEPAD_1_RB_PRESSED, telemetry.addData(GAMEPAD_1_RB_PRESSED, gamepad1RBPressed));
        telemetryItems.put(GAMEPAD_1_X_PRESSED, telemetry.addData(GAMEPAD_1_X_PRESSED, gamepad1XPressed));
        telemetryItems.put(GAMEPAD_2_LB_PRESSED, telemetry.addData(GAMEPAD_2_LB_PRESSED, gamepad2LBPressed));
        telemetryItems.put(GAMEPAD_2_RB_PRESSED, telemetry.addData(GAMEPAD_2_RB_PRESSED, gamepad2RBPressed));
        telemetryItems.put(GAMEPAD_2_A_PRESSED, telemetry.addData(GAMEPAD_2_A_PRESSED, gamepad2APressed));
        telemetryItems.put(SAVING, telemetry.addData(SAVING, saving));
        telemetry.addLine();

        telemetry.addLine(MOTOR_VALUES);
        telemetryItems.put(FRONT_LEFT_DRIVE_POWER, telemetry.addData(FRONT_LEFT_DRIVE_POWER, robot.getDrivetrain().getFrontLeft().getMotor().getPower()));
        telemetryItems.put(FRONT_RIGHT_DRIVE_POWER, telemetry.addData(FRONT_RIGHT_DRIVE_POWER, robot.getDrivetrain().getFrontRight().getMotor().getPower()));
        telemetryItems.put(REAR_LEFT_DRIVE_POWER, telemetry.addData(REAR_LEFT_DRIVE_POWER, robot.getDrivetrain().getRearLeft().getMotor().getPower()));
        telemetryItems.put(REAR_RIGHT_DRIVE_POWER, telemetry.addData(REAR_RIGHT_DRIVE_POWER, robot.getDrivetrain().getRearRight().getMotor().getPower()));
        telemetryItems.put(LEFT_LIFT_DRIVE_POWER, telemetry.addData(LEFT_LIFT_DRIVE_POWER, robot.getLiftDrives()[0].getPower()));
        telemetryItems.put(RIGHT_LIFT_DRIVE_POWER, telemetry.addData(RIGHT_LIFT_DRIVE_POWER, robot.getLiftDrives()[1].getPower()));
        telemetryItems.put(FRONT_LEFT_DRIVE_POSITION, telemetry.addData(FRONT_LEFT_DRIVE_POSITION, robot.getDrivetrain().getFrontLeft().getMotor().getCurrentPosition()));
        telemetryItems.put(FRONT_RIGHT_DRIVE_POSITION, telemetry.addData(FRONT_RIGHT_DRIVE_POSITION, robot.getDrivetrain().getFrontRight().getMotor().getCurrentPosition()));
        telemetryItems.put(REAR_LEFT_DRIVE_POSITION, telemetry.addData(REAR_LEFT_DRIVE_POSITION, robot.getDrivetrain().getRearLeft().getMotor().getCurrentPosition()));
        telemetryItems.put(REAR_RIGHT_DRIVE_POSITION, telemetry.addData(REAR_RIGHT_DRIVE_POSITION, robot.getDrivetrain().getRearRight().getMotor().getCurrentPosition()));
        telemetryItems.put(LEFT_LIFT_DRIVE_POSITION, telemetry.addData(LEFT_LIFT_DRIVE_POSITION, robot.getLiftDrives()[0].getCurrentPosition()));
        telemetryItems.put(RIGHT_LIFT_DRIVE_POSITION, telemetry.addData(RIGHT_LIFT_DRIVE_POSITION, robot.getLiftDrives()[1].getCurrentPosition()));
        telemetryItems.put(FRONT_LEFT_DRIVE_MODE, telemetry.addData(FRONT_LEFT_DRIVE_MODE, robot.getDrivetrain().getFrontLeft().getMotor().getMode() != null ? robot.getDrivetrain().getFrontLeft().getMotor().getMode().name() : robot.getDrivetrain().getFrontLeft().getMotor().getMode()));
        telemetryItems.put(FRONT_RIGHT_DRIVE_MODE, telemetry.addData(FRONT_RIGHT_DRIVE_MODE, robot.getDrivetrain().getFrontRight().getMotor().getMode() != null ? robot.getDrivetrain().getFrontRight().getMotor().getMode().name() : robot.getDrivetrain().getFrontRight().getMotor().getMode()));
        telemetryItems.put(REAR_LEFT_DRIVE_MODE, telemetry.addData(REAR_LEFT_DRIVE_MODE, robot.getDrivetrain().getRearLeft().getMotor().getMode() != null ? robot.getDrivetrain().getRearLeft().getMotor().getMode().name() : robot.getDrivetrain().getRearLeft().getMotor().getMode()));
        telemetryItems.put(REAR_RIGHT_DRIVE_MODE, telemetry.addData(REAR_RIGHT_DRIVE_MODE, robot.getDrivetrain().getRearRight().getMotor().getMode() != null ? robot.getDrivetrain().getRearRight().getMotor().getMode().name() : robot.getDrivetrain().getRearRight().getMotor().getMode()));
        telemetryItems.put(LEFT_LIFT_DRIVE_MODE, telemetry.addData(LEFT_LIFT_DRIVE_MODE, robot.getLiftDrives()[0].getMode() != null ? robot.getLiftDrives()[0].getMode().name() : robot.getLiftDrives()[0].getMode()));
        telemetryItems.put(RIGHT_LIFT_DRIVE_MODE, telemetry.addData(RIGHT_LIFT_DRIVE_MODE, robot.getLiftDrives()[1].getMode() != null ? robot.getLiftDrives()[1].getMode().name() : robot.getLiftDrives()[1].getMode()));
        telemetryItems.put(FRONT_LEFT_DRIVE_DIRECTION, telemetry.addData(FRONT_LEFT_DRIVE_DIRECTION, robot.getDrivetrain().getFrontLeft().getMotor().getDirection() != null ? robot.getDrivetrain().getFrontLeft().getMotor().getDirection().name() : robot.getDrivetrain().getFrontLeft().getMotor().getDirection()));
        telemetryItems.put(FRONT_RIGHT_DRIVE_DIRECTION, telemetry.addData(FRONT_RIGHT_DRIVE_DIRECTION, robot.getDrivetrain().getFrontRight().getMotor().getDirection() != null ? robot.getDrivetrain().getFrontRight().getMotor().getDirection().name() : robot.getDrivetrain().getFrontRight().getMotor().getDirection()));
        telemetryItems.put(REAR_LEFT_DRIVE_DIRECTION, telemetry.addData(REAR_LEFT_DRIVE_DIRECTION, robot.getDrivetrain().getRearLeft().getMotor().getDirection() != null ? robot.getDrivetrain().getRearLeft().getMotor().getDirection().name() : robot.getDrivetrain().getRearLeft().getMotor().getDirection()));
        telemetryItems.put(REAR_RIGHT_DRIVE_DIRECTION, telemetry.addData(REAR_RIGHT_DRIVE_DIRECTION, robot.getDrivetrain().getRearRight().getMotor().getDirection() != null ? robot.getDrivetrain().getRearRight().getMotor().getDirection().name() : robot.getDrivetrain().getRearRight().getMotor().getDirection()));
        telemetryItems.put(LEFT_LIFT_DRIVE_DIRECTION, telemetry.addData(LEFT_LIFT_DRIVE_DIRECTION, robot.getLiftDrives()[0].getDirection() != null ? robot.getLiftDrives()[0].getDirection().name() : robot.getLiftDrives()[0].getDirection()));
        telemetryItems.put(RIGHT_LIFT_DRIVE_DIRECTION, telemetry.addData(RIGHT_LIFT_DRIVE_DIRECTION, robot.getLiftDrives()[1].getDirection() != null ? robot.getLiftDrives()[1].getDirection().name() : robot.getLiftDrives()[1].getDirection()));
        telemetry.addLine();

        telemetry.addLine(SERVO_VALUES);
        telemetryItems.put(LEFT_AUXILIARY_SERVO_POSITION, telemetry.addData(LEFT_AUXILIARY_SERVO_POSITION, robot.getLeftServoPosition()));
        telemetryItems.put(RIGHT_AUXILIARY_SERVO_POSITION, telemetry.addData(RIGHT_AUXILIARY_SERVO_POSITION, robot.getRightServoPosition()));
        telemetryItems.put(PITCH_SERVO_POSITION, telemetry.addData(PITCH_SERVO_POSITION, robot.getPitchPosition()));
        telemetryItems.put(INTAKE_SERVO_POSITION, telemetry.addData(INTAKE_SERVO_POSITION, robot.getIntakePosition()));
        telemetryItems.put(GRIPPER_SERVO_POSITION, telemetry.addData(GRIPPER_SERVO_POSITION, robot.getGripperPosition()));
        telemetryItems.put(LEFT_AUXILIARY_SERVO_DIRECTION, telemetry.addData(LEFT_AUXILIARY_SERVO_DIRECTION, robot.getLeftServoA().getDirection() != null ? robot.getLeftServoA().getDirection().name() : robot.getLeftServoA().getDirection()));
        telemetryItems.put(RIGHT_AUXILIARY_SERVO_DIRECTION, telemetry.addData(RIGHT_AUXILIARY_SERVO_DIRECTION, robot.getRightServoA().getDirection() != null ? robot.getRightServoA().getDirection().name() : robot.getRightServoA().getDirection()));
        telemetryItems.put(PITCH_SERVO_DIRECTION, telemetry.addData(PITCH_SERVO_DIRECTION, robot.getPitch().getDirection() != null ? robot.getPitch().getDirection().name() : robot.getPitch().getDirection()));
        telemetryItems.put(INTAKE_SERVO_DIRECTION, telemetry.addData(INTAKE_SERVO_DIRECTION, robot.getIntake().getDirection() != null ? robot.getIntake().getDirection().name() : robot.getIntake().getDirection()));
        telemetryItems.put(GRIPPER_SERVO_DIRECTION, telemetry.addData(GRIPPER_SERVO_DIRECTION, robot.getGripper().getDirection() != null ? robot.getGripper().getDirection().name() : robot.getGripper().getDirection()));
        telemetry.addLine();

        telemetry.addLine(SENSOR_VALUES);
        telemetryItems.put(LEFT_DISTANCE, telemetry.addData(LEFT_DISTANCE, robot.getDistanceL()));
        telemetryItems.put(RIGHT_DISTANCE, telemetry.addData(RIGHT_DISTANCE, robot.getDistanceR()));
        telemetryItems.put(LIFT_DISTANCE, telemetry.addData(LIFT_DISTANCE, robot.getLiftHeight()));
        telemetryItems.put(LEFT_DISTANCE_UNIT, telemetry.addData(LEFT_DISTANCE_UNIT, robot.getUnit() != null ? robot.getUnit().name() : robot.getUnit()));
        telemetryItems.put(RIGHT_DISTANCE_UNIT, telemetry.addData(RIGHT_DISTANCE_UNIT, robot.getUnit() != null ? robot.getUnit().name() : robot.getUnit()));
        telemetryItems.put(LIFT_DISTANCE_UNIT, telemetry.addData(LIFT_DISTANCE_UNIT, robot.getUnit() != null ? robot.getUnit().name() : robot.getUnit()));
        telemetryItems.put(COLOR_DISTANCE, telemetry.addData(COLOR_DISTANCE, robot.getDistance()));
        telemetryItems.put(COLOR_DISTANCE_UNIT, telemetry.addData(COLOR_DISTANCE_UNIT, robot.getUnit()));
        telemetryItems.put(COLOR_RED, telemetry.addData(COLOR_RED, robot.getRed()));
        telemetryItems.put(COLOR_GREEN, telemetry.addData(COLOR_GREEN, robot.getGreen()));
        telemetryItems.put(COLOR_BLUE, telemetry.addData(COLOR_BLUE, robot.getBlue()));
        telemetryItems.put(COLOR_RED_NORMALIZED, telemetry.addData(COLOR_RED_NORMALIZED, robot.getNormalizedRed()));
        telemetryItems.put(COLOR_GREEN_NORMALIZED, telemetry.addData(COLOR_GREEN_NORMALIZED, robot.getNormalizedGreen()));
        telemetryItems.put(COLOR_BLUE_NORMALIZED, telemetry.addData(COLOR_BLUE_NORMALIZED, robot.getNormalizedBlue()));
        telemetryItems.put(COLOR_LIGHT, telemetry.addData(COLOR_LIGHT, robot.getLight()));
        telemetryItems.put(COLOR_RAW_LIGHT, telemetry.addData(COLOR_RAW_LIGHT, robot.getRawLight()));
        telemetryItems.put(COLOR_LIGHT_NORMALIZED, telemetry.addData(COLOR_LIGHT_NORMALIZED, robot.getNormalizedLight()));
        telemetry.addLine();
    }

    private final Runnable diagnostics = () -> {
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + LEFT_TRIGGER)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.left_trigger));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + RIGHT_TRIGGER)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.right_trigger));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + LEFT_BUMPER)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.left_bumper));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + RIGHT_BUMPER)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.right_bumper));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + LEFT_STICK_X)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.left_stick_x));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + LEFT_STICK_Y)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.left_stick_y));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + RIGHT_STICK_X)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.right_stick_x));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + RIGHT_STICK_Y)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.right_stick_y));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + BUTTON_A)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.a));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + BUTTON_B)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.b));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + BUTTON_X)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.x));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + BUTTON_Y)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.y));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + DPAD_DOWN)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.dpad_down));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + DPAD_LEFT)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.dpad_left));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + DPAD_UP)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.dpad_up));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_VALUES + DPAD_RIGHT)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1.dpad_right));

        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + LEFT_TRIGGER)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.left_trigger));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + RIGHT_TRIGGER)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.right_trigger));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + LEFT_BUMPER)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.left_bumper));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + RIGHT_BUMPER)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.right_bumper));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + LEFT_STICK_X)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.left_stick_x));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + LEFT_STICK_Y)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.left_stick_y));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + RIGHT_STICK_X)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.right_stick_x));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + RIGHT_STICK_Y)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.right_stick_y));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + BUTTON_A)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.a));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + BUTTON_B)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.b));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + BUTTON_X)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.x));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + BUTTON_Y)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.y));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + DPAD_DOWN)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.dpad_down));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + DPAD_LEFT)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.dpad_left));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + DPAD_UP)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.dpad_up));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_VALUES + DPAD_RIGHT)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2.dpad_right));

        Optional.ofNullable(telemetryItems.get(PITCH_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(pitchPosition));
        Optional.ofNullable(telemetryItems.get(PITCH_TOGGLED_OUT)).ifPresent((Telemetry.Item item) -> item.setValue(pitchToggledOut));
        Optional.ofNullable(telemetryItems.get(INTAKE_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(intakePosition));
        Optional.ofNullable(telemetryItems.get(INTAKE_TOGGLED_OUT)).ifPresent((Telemetry.Item item) -> item.setValue(intakeToggledOut));
        Optional.ofNullable(telemetryItems.get(GRIPPER_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(gripperPosition));
        Optional.ofNullable(telemetryItems.get(GRIPPER_TOGGLED_CLOSED)).ifPresent((Telemetry.Item item) -> item.setValue(gripperToggledClosed));
        Optional.ofNullable(telemetryItems.get(FOUNDATION_ARMS_TOGGLED_OUT)).ifPresent((Telemetry.Item item) -> item.setValue(foundationArmsToggledOut));
        Optional.ofNullable(telemetryItems.get(DRIVE_SPEED_CAP)).ifPresent((Telemetry.Item item) -> item.setValue(driveSpeedCap));
        Optional.ofNullable(telemetryItems.get(DIAGNOSTIC_MODE_TOGGLED)).ifPresent((Telemetry.Item item) -> item.setValue(diagnosticModeToggled));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_RB_PRESSED)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1RBPressed));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_1_X_PRESSED)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad1XPressed));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_LB_PRESSED)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2LBPressed));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_RB_PRESSED)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2RBPressed));
        Optional.ofNullable(telemetryItems.get(GAMEPAD_2_A_PRESSED)).ifPresent((Telemetry.Item item) -> item.setValue(gamepad2APressed));
        Optional.ofNullable(telemetryItems.get(SAVING)).ifPresent((Telemetry.Item item) -> item.setValue(saving));

        Optional.ofNullable(telemetryItems.get(FRONT_LEFT_DRIVE_POWER)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getFrontLeft().getMotor().getPower()));
        Optional.ofNullable(telemetryItems.get(FRONT_RIGHT_DRIVE_POWER)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getFrontRight().getMotor().getPower()));
        Optional.ofNullable(telemetryItems.get(REAR_LEFT_DRIVE_POWER)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getRearLeft().getMotor().getPower()));
        Optional.ofNullable(telemetryItems.get(REAR_RIGHT_DRIVE_POWER)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getRearRight().getMotor().getPower()));
        Optional.ofNullable(telemetryItems.get(LEFT_LIFT_DRIVE_POWER)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getLiftDrives()[0].getPower()));
        Optional.ofNullable(telemetryItems.get(RIGHT_LIFT_DRIVE_POWER)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getLiftDrives()[1].getPower()));
        Optional.ofNullable(telemetryItems.get(FRONT_LEFT_DRIVE_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getFrontLeft().getMotor().getCurrentPosition()));
        Optional.ofNullable(telemetryItems.get(FRONT_RIGHT_DRIVE_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getFrontRight().getMotor().getCurrentPosition()));
        Optional.ofNullable(telemetryItems.get(REAR_LEFT_DRIVE_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getRearLeft().getMotor().getCurrentPosition()));
        Optional.ofNullable(telemetryItems.get(REAR_RIGHT_DRIVE_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getRearRight().getMotor().getCurrentPosition()));
        Optional.ofNullable(telemetryItems.get(LEFT_LIFT_DRIVE_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getLiftDrives()[0].getCurrentPosition()));
        Optional.ofNullable(telemetryItems.get(RIGHT_LIFT_DRIVE_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getLiftDrives()[1].getCurrentPosition()));
        Optional.ofNullable(telemetryItems.get(FRONT_LEFT_DRIVE_MODE)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getFrontLeft().getMotor().getMode() != null ? robot.getDrivetrain().getFrontLeft().getMotor().getMode().name() : robot.getDrivetrain().getFrontLeft().getMotor().getMode()));
        Optional.ofNullable(telemetryItems.get(FRONT_RIGHT_DRIVE_MODE)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getFrontRight().getMotor().getMode() != null ? robot.getDrivetrain().getFrontRight().getMotor().getMode().name() : robot.getDrivetrain().getFrontRight().getMotor().getMode()));
        Optional.ofNullable(telemetryItems.get(REAR_LEFT_DRIVE_MODE)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getRearLeft().getMotor().getMode() != null ? robot.getDrivetrain().getRearLeft().getMotor().getMode().name() : robot.getDrivetrain().getRearLeft().getMotor().getMode()));
        Optional.ofNullable(telemetryItems.get(REAR_RIGHT_DRIVE_MODE)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getRearRight().getMotor().getMode() != null ? robot.getDrivetrain().getRearRight().getMotor().getMode().name() : robot.getDrivetrain().getRearRight().getMotor().getMode()));
        Optional.ofNullable(telemetryItems.get(LEFT_LIFT_DRIVE_MODE)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getLiftDrives()[0].getMode() != null ? robot.getLiftDrives()[0].getMode().name() : robot.getLiftDrives()[0].getMode()));
        Optional.ofNullable(telemetryItems.get(RIGHT_LIFT_DRIVE_MODE)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getLiftDrives()[1].getMode() != null ? robot.getLiftDrives()[1].getMode().name() : robot.getLiftDrives()[1].getMode()));
        Optional.ofNullable(telemetryItems.get(FRONT_LEFT_DRIVE_DIRECTION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getFrontLeft().getMotor().getDirection() != null ? robot.getDrivetrain().getFrontLeft().getMotor().getDirection().name() : robot.getDrivetrain().getFrontLeft().getMotor().getDirection()));
        Optional.ofNullable(telemetryItems.get(FRONT_RIGHT_DRIVE_DIRECTION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getFrontRight().getMotor().getDirection() != null ? robot.getDrivetrain().getFrontRight().getMotor().getDirection().name() : robot.getDrivetrain().getFrontRight().getMotor().getDirection()));
        Optional.ofNullable(telemetryItems.get(REAR_LEFT_DRIVE_DIRECTION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getRearLeft().getMotor().getDirection() != null ? robot.getDrivetrain().getRearLeft().getMotor().getDirection().name() : robot.getDrivetrain().getRearLeft().getMotor().getDirection()));
        Optional.ofNullable(telemetryItems.get(REAR_RIGHT_DRIVE_DIRECTION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDrivetrain().getRearRight().getMotor().getDirection() != null ? robot.getDrivetrain().getRearRight().getMotor().getDirection().name() : robot.getDrivetrain().getRearRight().getMotor().getDirection()));
        Optional.ofNullable(telemetryItems.get(LEFT_LIFT_DRIVE_DIRECTION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getLiftDrives()[0].getDirection() != null ? robot.getLiftDrives()[0].getDirection().name() : robot.getLiftDrives()[0].getDirection()));
        Optional.ofNullable(telemetryItems.get(RIGHT_LIFT_DRIVE_DIRECTION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getLiftDrives()[1].getDirection() != null ? robot.getLiftDrives()[1].getDirection().name() : robot.getLiftDrives()[1].getDirection()));

        Optional.ofNullable(telemetryItems.get(LEFT_AUXILIARY_SERVO_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getLeftServoPosition()));
        Optional.ofNullable(telemetryItems.get(RIGHT_AUXILIARY_SERVO_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getRightServoPosition()));
        Optional.ofNullable(telemetryItems.get(PITCH_SERVO_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getPitchPosition()));
        Optional.ofNullable(telemetryItems.get(INTAKE_SERVO_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getIntakePosition()));
        Optional.ofNullable(telemetryItems.get(GRIPPER_SERVO_POSITION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getGripperPosition()));
        Optional.ofNullable(telemetryItems.get(LEFT_AUXILIARY_SERVO_DIRECTION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getLeftServoA().getDirection() != null ? robot.getLeftServoA().getDirection().name() : robot.getLeftServoA().getDirection()));
        Optional.ofNullable(telemetryItems.get(RIGHT_AUXILIARY_SERVO_DIRECTION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getRightServoA().getDirection() != null ? robot.getRightServoA().getDirection().name() : robot.getRightServoA().getDirection()));
        Optional.ofNullable(telemetryItems.get(PITCH_SERVO_DIRECTION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getPitch().getDirection() != null ? robot.getPitch().getDirection().name() : robot.getPitch().getDirection()));
        Optional.ofNullable(telemetryItems.get(INTAKE_SERVO_DIRECTION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getIntake().getDirection() != null ? robot.getIntake().getDirection().name() : robot.getIntake().getDirection()));
        Optional.ofNullable(telemetryItems.get(GRIPPER_SERVO_DIRECTION)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getGripper().getDirection() != null ? robot.getGripper().getDirection().name() : robot.getGripper().getDirection()));

        Optional.ofNullable(telemetryItems.get(LEFT_DISTANCE)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDistanceL()));
        Optional.ofNullable(telemetryItems.get(RIGHT_DISTANCE)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDistanceR()));
        Optional.ofNullable(telemetryItems.get(LIFT_DISTANCE)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getLiftHeight()));
        Optional.ofNullable(telemetryItems.get(LEFT_DISTANCE_UNIT)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getUnit() != null ? robot.getUnit().name() : robot.getUnit()));
        Optional.ofNullable(telemetryItems.get(RIGHT_DISTANCE_UNIT)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getUnit() != null ? robot.getUnit().name() : robot.getUnit()));
        Optional.ofNullable(telemetryItems.get(LIFT_DISTANCE_UNIT)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getUnit() != null ? robot.getUnit().name() : robot.getUnit()));
        Optional.ofNullable(telemetryItems.get(COLOR_DISTANCE)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getDistance()));
        Optional.ofNullable(telemetryItems.get(COLOR_DISTANCE_UNIT)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getUnit()));
        Optional.ofNullable(telemetryItems.get(COLOR_RED)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getRed()));
        Optional.ofNullable(telemetryItems.get(COLOR_GREEN)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getGreen()));
        Optional.ofNullable(telemetryItems.get(COLOR_BLUE)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getBlue()));
        Optional.ofNullable(telemetryItems.get(COLOR_RED_NORMALIZED)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getNormalizedRed()));
        Optional.ofNullable(telemetryItems.get(COLOR_GREEN_NORMALIZED)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getNormalizedGreen()));
        Optional.ofNullable(telemetryItems.get(COLOR_BLUE_NORMALIZED)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getNormalizedBlue()));
        Optional.ofNullable(telemetryItems.get(COLOR_LIGHT)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getLight()));
        Optional.ofNullable(telemetryItems.get(COLOR_RAW_LIGHT)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getRawLight()));
        Optional.ofNullable(telemetryItems.get(COLOR_LIGHT_NORMALIZED)).ifPresent((Telemetry.Item item) -> item.setValue(robot.getNormalizedLight()));
    };

    @Override
    public void init() {
        telemetry.setMsTransmissionInterval(TELEMETRY_TRANSMISSION_INTERVAL);

        robot              = CompetitionRobot.instantiate(hardwareMap);
        motorNames         = robot.getMotorNames();
        servoNames         = robot.getServoNames();
        motorVarNames      = robot.getMotorVarNames();
        servoVarNames      = robot.getServoVarNames();
        recorderByPower    = new Recorder(false);
    }

    @Override
    public void init_loop() {
        super.init_loop();
    }

    @Override
    public void start() {
        super.start();
        recorderByPower.record(this);
    }

    @Override
    public void loop() {
        if (!gamepad1.x) {
            if (gamepad1XPressed) {
                driveSpeedCap = driveSpeedCap == DRIVE_SPEED_CAPACITY ? 1 : DRIVE_SPEED_CAPACITY;
            }
            gamepad1XPressed = false;
        } else {
            gamepad1XPressed = true;
        }

        robot.driveBySticks(new MecanumDriveInput(adjustedValueOf(gamepad1.left_stick_x) * driveSpeedCap, adjustedValueOf(gamepad1.left_stick_y) * driveSpeedCap, adjustedValueOf(-gamepad1.right_stick_x) * driveSpeedCap));
        robot.setLiftPower(adjustedValueOf(gamepad2.left_trigger) != MIN_TRIGGER_VALUE ? adjustedValueOf(gamepad2.left_trigger) : adjustedValueOf(-gamepad2.right_trigger) != MIN_TRIGGER_VALUE ? adjustedValueOf(-gamepad2.right_trigger) : DEFAULT_VELOCITY_CHANGE);

        if (!gamepad1.right_bumper) {
            if (gamepad1RBPressed) {
                foundationArmsToggledOut = !foundationArmsToggledOut;
                robot.setLeftServoPosition(foundationArmsToggledOut ? -1 : 0);
                robot.setRightServoPosition(foundationArmsToggledOut ? 1 : 0);
            }
            gamepad1RBPressed = false;
        } else {
            gamepad1RBPressed = true;
        }

        if (!gamepad2.left_bumper) {
            if (gamepad2LBPressed) {
                pitchToggledOut = !pitchToggledOut;
                pitchPosition = pitchToggledOut ? DEFAULT_PITCH_POSITION : PITCH_MIN;
            }
            gamepad2LBPressed = false;
        } else {
            gamepad2LBPressed = true;
        }

        if (!gamepad2.right_bumper) {
            if (gamepad2RBPressed) {
                gripperToggledClosed = !gripperToggledClosed;
                gripperPosition = gripperToggledClosed ? DEFAULT_GRIPPER_POSITION : ARTIFICIAL_SERVO_MAX;
            }
            gamepad2RBPressed = false;
        } else {
            gamepad2RBPressed = true;
        }

        if (!gamepad2.a) {
            if (gamepad2APressed) {
                intakeToggledOut = !intakeToggledOut;
                intakePosition = intakeToggledOut ? ARTIFICIAL_SERVO_MIN : DEFAULT_INTAKE_POSITION;
            }
            gamepad2APressed = false;
        } else {
            gamepad2APressed = true;
        }

        robot.setPitchPosition(Range.scale(pitchPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX));
        robot.setIntakePosition(Range.scale(intakePosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX));
        robot.setGripperPosition(Range.scale(gripperPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX));

        if (gamepad1.left_bumper) {
            diagnosticModeToggled = !diagnosticModeToggled;
            if (diagnosticModeToggled) {
                initTelemetryData();
                telemetry.addAction(diagnostics);
            } else {
                telemetry.removeAction(diagnostics);
                clearTelemetry();
            }
        }

        saving = gamepad1.y;

        onStateChanged.run();
    }

    @Override
    public void stop() {
        super.stop();
        if (saving) {
            Thread th = new Thread(() -> new AutonomousFileGenerator().writeFile(recorderByPower.optimize(powerOptimizer).getStatementsSeparatedBy(System.lineSeparator()), AutonomousFileGenerator.getSimpleDateFormat(), AutonomousFileGenerator.TEXT_FILE_EXTENSION));
            th.setPriority(RECORDING_THREAD_PRIORITY);
            th.start();
        }
    }

    @NotNull
    @Override
    public LinkedList<String> getStatements(boolean byEncoder) {
        LinkedList<String> statements = new LinkedList<>();
        if (byEncoder) {
            for (int i = 0; i < motorNames.length; i++) {
                statements.add(motorVarNames[i] + ".setTargetPosition(" + hardwareMap.dcMotor.get(motorNames[i]).getCurrentPosition() + ");");
            }
            for (int i = 0; i < servoNames.length; i++) {
                statements.add(servoVarNames[i] + ".setPosition(" + hardwareMap.servo.get(servoNames[i]).getPosition() + ");");
            }
        } else {
            statements.add("robot.driveBySticks(new MecanumDriveInput(" + adjustedValueOf(gamepad1.left_stick_x) * DRIVE_SPEED_CAPACITY + ", " + adjustedValueOf(gamepad1.left_stick_y) * DRIVE_SPEED_CAPACITY + ", " + adjustedValueOf(-gamepad1.right_stick_x) * DRIVE_SPEED_CAPACITY + "));");
            statements.add("robot.setLiftPower(" + (adjustedValueOf(gamepad2.left_trigger) != MIN_TRIGGER_VALUE ? adjustedValueOf(gamepad2.left_trigger) : adjustedValueOf(gamepad2.right_trigger) != MIN_TRIGGER_VALUE ? adjustedValueOf(-gamepad2.right_trigger) : DEFAULT_VELOCITY_CHANGE) + ");");
            statements.add("robot.setLeftServoPosition(" + (foundationArmsToggledOut ? -1 : 0) + ");");
            statements.add("robot.setRightServoPosition(" + (foundationArmsToggledOut ? 1 : 0) + ");");
            statements.add("robot.setPitchPosition(" + Range.scale(pitchPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX) + ");");
            statements.add("robot.setIntakePosition(" + Range.scale(intakePosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX) + ");");
            statements.add("robot.setGripperPosition(" + Range.scale(gripperPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX) + ");");
        }
        return statements;
    }

    @Override
    public void setOnStateChangedCallback(Runnable callback) {
        onStateChanged = callback;
    }

    private double adjustedValueOf(double gamepadValue) {
        return gamepadValue * gamepadValue * gamepadValue;
    }
    
    private void clearTelemetry() {
        telemetry.clearAll();
        telemetryItems.clear();
    }
}