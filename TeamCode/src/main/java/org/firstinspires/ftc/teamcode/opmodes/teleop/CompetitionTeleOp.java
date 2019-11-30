package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobotI;
import org.firstinspires.ftc.teamcode.robots.drivetrains.mecanum.MecanumDriveInput;
import org.firstinspires.ftc.teamcode.utils.AutonomousFileGenerator;
import org.firstinspires.ftc.teamcode.utils.CommonOptimizations;
import org.firstinspires.ftc.teamcode.utils.Recordable;
import org.firstinspires.ftc.teamcode.utils.Recorder;
import org.firstinspires.ftc.teamcode.utils.RecorderI;
import org.firstinspires.ftc.teamcode.utils.RecordingOptimizer;
import org.firstinspires.ftc.teamcode.utils.RecordingOptimizerI;
import org.firstinspires.ftc.teamcode.utils.RecordingType;

import java.util.LinkedList;
import java.util.function.Function;

@TeleOp(name="CompetitionRobot")
public class CompetitionTeleOp extends OpMode implements Recordable {
    private static final double MIN_TRIGGER_VALUE = 0;

    private static final double AUX_ARM_SPEED = 0.01;

    private static final double DRIVE_SPEED_CAPACITY = .9;

    private static final double YAW_SPEED_CAPACITY = 1 / 32;
    private static final double GRIPPER_SPEED_CAPACITY = 1 / 24;

    private static final byte LOW_SPEED_CONTROL_FACTOR = 3; // This is exponential ie stickValue^LOW_SPEED_CONTROL_FACTOR

    private static final byte DEFAULT_PITCH_POSITION = 1;
    private static final byte DEFAULT_YAW_POSITION = 0;
    private static final byte DEFAULT_GRIPPER_POSITION = 0;

    private static final double LEFT_GRIPPER_POSITION = 1;
    private static final double CENTER_GRIPPER_POSITION = -0.15;
    private static final double RIGHT_GRIPPER_POSITION = -0.85;

    private static final byte ARTIFICIAL_SERVO_MIN = -1;
    private static final byte ARTIFICIAL_SERVO_MAX = 1;

    private static final byte ACTUAL_SERVO_MIN = 0;
    private static final byte ACTUAL_SERVO_MAX = 1;

    private static final double DEFAULT_VELOCITY_CHANGE = 0;
    private static final double DEFAULT_POSITION_CHANGE = 0;

    private static final byte RECORDING_THREAD_PRIORITY = 9;

    private static final Function<LinkedList<String>, LinkedList<String>> powerOptimizer = (LinkedList<String> statements) -> new LinkedList<>(new CommonOptimizations().optimizeSleepCalls(statements, 8, 7));

    private static final Function<LinkedList<String>, LinkedList<String>> positionOptimizer = (LinkedList<String> statements) -> new CommonOptimizations().optimizeSleepCalls(statements, 8, 0);

    private CompetitionRobotI robot;
    private Runnable onStateChanged = () -> {
    };

    private String[] motorNames;
    private String[] servoNames;
    private String[] motorVarNames;
    private String[] servoVarNames;

    private RecorderI recorderByPower;

    private RecordingOptimizerI recordingOptimizer;

    private double pitchPosition = DEFAULT_PITCH_POSITION;
    private double yawPosition = DEFAULT_YAW_POSITION;
    private double gripperPosition = DEFAULT_GRIPPER_POSITION;
    private boolean gripperToggledClosed = false;

    private boolean saving = false;

    @Override
    public void init() {
        robot = CompetitionRobot.instantiate(hardwareMap);

        motorNames = robot.getMotorNames();
        servoNames = robot.getServoNames();
        motorVarNames = robot.getMotorVarNames();
        servoVarNames = robot.getServoVarNames();

        recorderByPower = new Recorder(false);

        recordingOptimizer = new RecordingOptimizer().setOptimizerFunctionFor(RecordingType.POSITION, positionOptimizer).setOptimizerFunctionFor(RecordingType.POWER, powerOptimizer);
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
        robot.driveBySticks(new MecanumDriveInput(adjustedValueOf(gamepad1.left_stick_x) * DRIVE_SPEED_CAPACITY, adjustedValueOf(gamepad1.left_stick_y) * DRIVE_SPEED_CAPACITY, adjustedValueOf(-gamepad1.right_stick_x) * DRIVE_SPEED_CAPACITY));
        robot.setLiftPower(adjustedValueOf(gamepad2.left_trigger) != MIN_TRIGGER_VALUE ? adjustedValueOf(gamepad2.left_trigger) : adjustedValueOf(gamepad2.right_trigger) != MIN_TRIGGER_VALUE ? adjustedValueOf(-gamepad2.right_trigger) : DEFAULT_VELOCITY_CHANGE);
        robot.incrLeft(gamepad1.x ? AUX_ARM_SPEED : gamepad2.y ? -AUX_ARM_SPEED : DEFAULT_POSITION_CHANGE);
        robot.incrRight(gamepad1.a ? AUX_ARM_SPEED : gamepad2.b ? -AUX_ARM_SPEED : DEFAULT_POSITION_CHANGE);

        pitchPosition *= gamepad2.left_bumper ? ARTIFICIAL_SERVO_MIN : ARTIFICIAL_SERVO_MAX;

        if (gamepad2.x) {
            yawPosition = LEFT_GRIPPER_POSITION;
        } else if (gamepad2.y) {
            yawPosition = CENTER_GRIPPER_POSITION;
        } else if (gamepad2.b) {
            yawPosition = RIGHT_GRIPPER_POSITION;
        } else {
            yawPosition -= gamepad2.left_stick_x * YAW_SPEED_CAPACITY;
            yawPosition = yawPosition < ARTIFICIAL_SERVO_MIN ? ARTIFICIAL_SERVO_MIN : yawPosition > ARTIFICIAL_SERVO_MAX ? ARTIFICIAL_SERVO_MAX : yawPosition;
        }

        gripperPosition += gamepad2.right_stick_y * GRIPPER_SPEED_CAPACITY;
        gripperPosition = gripperPosition < ARTIFICIAL_SERVO_MIN ? ARTIFICIAL_SERVO_MIN : gripperPosition > ARTIFICIAL_SERVO_MAX ? ARTIFICIAL_SERVO_MAX : gripperPosition;

        if (gamepad2.right_bumper) {
            gripperToggledClosed = !gripperToggledClosed;
            gripperPosition = gripperToggledClosed ? ARTIFICIAL_SERVO_MIN : ARTIFICIAL_SERVO_MAX;
        }

        robot.setPitchPosition(Range.scale(pitchPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX));
        robot.setYawPosition(Range.scale(yawPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX));
        robot.setGripperPosition(Range.scale(gripperPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX));

        saving = gamepad1.y;

        onStateChanged.run();
    }

    @Override
    public void stop() {
        super.stop();
        if (saving) {
            Thread th = new Thread(() -> new AutonomousFileGenerator().writeFile(recorderByPower.getStatementsSeparatedBy(System.lineSeparator()), AutonomousFileGenerator.getSimpleDateFormat(), AutonomousFileGenerator.TEXT_FILE_EXTENSION));
            th.setPriority(RECORDING_THREAD_PRIORITY);
            th.start();
        }
    }

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
            statements.add("robot.incrLeft(" + (gamepad2.x ? AUX_ARM_SPEED : gamepad2.y ? -AUX_ARM_SPEED : DEFAULT_POSITION_CHANGE) + ");");
            statements.add("robot.incrRight(" + (gamepad2.a ? AUX_ARM_SPEED : gamepad2.b ? -AUX_ARM_SPEED : DEFAULT_POSITION_CHANGE) + ");");
            statements.add("robot.setPitchPosition(" + Range.scale(pitchPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX) + ");");
            statements.add("robot.setYawPosition(" + Range.scale(yawPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX) + ");");
            statements.add("robot.setGripperPosition(" + Range.scale(gripperPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX) + ");");
        }
        return statements;
    }

    @Override
    public void setOnStateChangedCallback(Runnable callback) {
        onStateChanged = callback;
    }

    private double adjustedValueOf(double gamepadValue) {
        return Math.pow(gamepadValue, LOW_SPEED_CONTROL_FACTOR) * (gamepadValue < 0 ? -1 : 1);
    }
}