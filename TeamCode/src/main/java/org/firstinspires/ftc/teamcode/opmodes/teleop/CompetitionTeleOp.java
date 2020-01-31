package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.annotations.Competition;
import org.firstinspires.ftc.teamcode.annotations.Observable;
import org.firstinspires.ftc.teamcode.control.Controller;
import org.firstinspires.ftc.teamcode.control.ControllerImpl;
import org.firstinspires.ftc.teamcode.control.robots.CompetitionRobot;
import org.firstinspires.ftc.teamcode.control.robots.CompetitionRobotI;
import org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum.MecanumDriveInput;
import org.firstinspires.ftc.teamcode.utils.AutonomousFileGenerator;
import org.firstinspires.ftc.teamcode.utils.CommonOptimizations;
import org.firstinspires.ftc.teamcode.utils.ObjectTriplet;
import org.firstinspires.ftc.teamcode.utils.Recordable;
import org.firstinspires.ftc.teamcode.utils.Recorder;
import org.firstinspires.ftc.teamcode.utils.RecorderI;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Competition
@TeleOp(name="CompetitionRobot")
public class CompetitionTeleOp extends OpMode implements Recordable {
    private static final int TELEMETRY_TRANSMISSION_INTERVAL    = 125;
    private static final String GAMEPAD_1                       = "Gamepad 1";
    private static final String GAMEPAD_2                       = "Gamepad 2";

    private static final double MIN_TRIGGER_VALUE = 0x0;

    private static final double DRIVE_SPEED_CAPACITY = .8;

    private static final byte LOW_SPEED_CONTROL_FACTOR = 0x3; // This is exponential ie stickValue^LOW_SPEED_CONTROL_FACTOR

    private static final double DEFAULT_PITCH_POSITION = 0.3;
    private static final double DEFAULT_GRIPPER_POSITION = 0.5;

    private static final byte LIFT_MIN = 0x0;

    private static final byte CD_THRESHOLD = 0x37;

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

    private Controller controller1;
    private Controller controller2;

    private volatile List<ObjectTriplet<Observable, Field, Object>> observableFields;
    private volatile List<ObjectTriplet<Observable, Method, Object>> observableMethods;

    @Observable(key = "Pitch Position")
    private double pitchPosition = PITCH_MIN;
    @Observable(key = "Pitch Toggled Out")
    private boolean pitchToggledOut = false;
    @Observable(key = "Gripper Position")
    private double gripperPosition = DEFAULT_GRIPPER_POSITION;
    @Observable(key = "Gripper Toggled Closed")
    private boolean gripperToggledClosed = true;

    @Observable(key = "Foundation Arms Toggled Out")
    private boolean foundationArmsToggledOut = false;

    @Observable(key = "Drive Speed Cap")
    private double driveSpeedCap = DRIVE_SPEED_CAPACITY;

    @Observable(key = "Diagnostic Mode Toggled")
    private boolean diagnosticModeToggled = false;

    @Observable(key = "Gamepad 1 LB Pressed")
    private boolean gamepad1LBPressed;
    @Observable(key = "Gamepad 1 RB Pressed")
    private boolean gamepad1RBPressed;
    @Observable(key = "Gamepad1 X Pressed")
    private boolean gamepad1XPressed;

    @Observable(key = "Gamepad 2 LB Pressed")
    private boolean gamepad2LBPressed;
    @Observable(key = "Gamepad 2 RB Pressed")
    private boolean gamepad2RBPressed;
    @Observable(key = "Gamepad 2 A Pressed")
    private boolean gamepad2BPressed;

    @Observable(key = "Overriding Automation")
    private boolean overridingAutomation = false;

    @Observable(key = "Saving")
    private boolean saving = false;
    
    private HashMap<String, Telemetry.Item> telemetryItems = new HashMap<>();

    private final Runnable diagnostics = () -> updateTelemetryData(observableFields, observableMethods, telemetryItems);

    @Override
    public void init() {
        telemetry.setMsTransmissionInterval(TELEMETRY_TRANSMISSION_INTERVAL);

        Object cachedObj = FtcRobotControllerActivity.soloInstance().getCachedRobotObject();
        robot              = cachedObj instanceof CompetitionRobotI ? (CompetitionRobot) ((CompetitionRobotI) cachedObj).refresh() : CompetitionRobot.instantiate(hardwareMap);
        FtcRobotControllerActivity.soloInstance().setCachedRobotObject(cachedObj);
        motorNames         = robot.getMotorNames();
        servoNames         = robot.getServoNames();
        motorVarNames      = robot.getMotorVarNames();
        servoVarNames      = robot.getServoVarNames();
        recorderByPower    = new Recorder(false);
        controller1 = new ControllerImpl(gamepad1, GAMEPAD_1);
        controller2 = new ControllerImpl(gamepad2, GAMEPAD_2);
        new Thread(() -> {
            observableFields = new LinkedList<>(getObservables(CompetitionTeleOp.class.getDeclaredFields(), this));
            observableMethods = new LinkedList<>();
            observableMethods.addAll(getObservables(Controller.class.getDeclaredMethods(), controller1));
            observableMethods.addAll(getObservables(Controller.class.getDeclaredMethods(), controller2));
            observableMethods.addAll(getObservables(CompetitionRobot.class.getDeclaredMethods(), robot));
        }).start();
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
        if (!controller1.x()) {
            if (gamepad1XPressed) {
                driveSpeedCap = driveSpeedCap == DRIVE_SPEED_CAPACITY ? 1 : DRIVE_SPEED_CAPACITY;
            }
            gamepad1XPressed = false;
        } else {
            gamepad1XPressed = true;
        }

        robot.driveBySticks(new MecanumDriveInput(adjustedValueOf(controller1.leftStickX()) * driveSpeedCap, adjustedValueOf(controller1.leftStickY()) * driveSpeedCap, adjustedValueOf(-controller1.rightStickX()) * driveSpeedCap));
        robot.setLiftPower(adjustedValueOf(controller2.leftTrigger()) != MIN_TRIGGER_VALUE ? adjustedValueOf(controller2.leftTrigger()) : adjustedValueOf(-controller2.rightTrigger()) != MIN_TRIGGER_VALUE ? adjustedValueOf(-controller2.rightTrigger()) : DEFAULT_VELOCITY_CHANGE);

        if (!controller1.rightBumper()) {
            if (gamepad1RBPressed) {
                foundationArmsToggledOut = !foundationArmsToggledOut;
                robot.setLeftServoPosition(foundationArmsToggledOut ? -1 : 0);
                robot.setRightServoPosition(foundationArmsToggledOut ? 1 : 0);
            }
            gamepad1RBPressed = false;
        } else {
            gamepad1RBPressed = true;
        }

        if (!controller2.b()) {
            if (gamepad2BPressed) {
                overridingAutomation = !overridingAutomation;
            }
            gamepad2BPressed = false;
        } else {
            gamepad2BPressed = true;
        }

        if (!controller2.leftBumper()) {
            if (gamepad2LBPressed) {
                pitchToggledOut = !pitchToggledOut;
                pitchPosition = pitchToggledOut ? DEFAULT_PITCH_POSITION : PITCH_MIN;
            }
            gamepad2LBPressed = false;
        } else {
            gamepad2LBPressed = true;
        }

        if (!controller2.rightBumper()) {
            if (gamepad2RBPressed) {
                gripperToggledClosed = !gripperToggledClosed;
                gripperPosition = gripperToggledClosed ? DEFAULT_GRIPPER_POSITION : ARTIFICIAL_SERVO_MAX;
            }
            gamepad2RBPressed = false;
        } else {
            gamepad2RBPressed = true;
        }

        if (!overridingAutomation) {
            if (robot.getDistance() < CD_THRESHOLD) {
                gripperToggledClosed = true;
                gripperPosition = ARTIFICIAL_SERVO_MAX;
                robot.setPattern(RevBlinkinLedDriver.BlinkinPattern.DARK_BLUE);
            } else {
                robot.setPattern(RevBlinkinLedDriver.BlinkinPattern.WHITE);
            }
        } else {
            robot.setPattern(RevBlinkinLedDriver.BlinkinPattern.SHOT_RED);
        }

        robot.setPitchPosition(Range.scale(pitchPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX));
        robot.setGripperPosition(Range.scale(gripperPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX));

        if (!controller1.leftBumper()) {
            if (gamepad1LBPressed) {
                diagnosticModeToggled = !diagnosticModeToggled;
                if (diagnosticModeToggled) {
                    initTelemetryData(observableFields, observableMethods, telemetryItems);
                    telemetry.addAction(diagnostics);
                } else {
                    telemetry.removeAction(diagnostics);
                    clearTelemetry();
                }
            }
            gamepad1LBPressed = false;
        } else {
            gamepad1LBPressed = true;
        }

        saving = controller1.y();

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
            statements.add("robot.driveBySticks(new MecanumDriveInput(" + adjustedValueOf(controller1.leftStickX()) * DRIVE_SPEED_CAPACITY + ", " + adjustedValueOf(controller1.leftStickY()) * DRIVE_SPEED_CAPACITY + ", " + adjustedValueOf(-controller1.rightStickX()) * DRIVE_SPEED_CAPACITY + "));");
            statements.add("robot.setLiftPower(" + (adjustedValueOf(controller2.leftTrigger()) != MIN_TRIGGER_VALUE ? adjustedValueOf(controller2.leftTrigger()) : adjustedValueOf(controller2.rightTrigger()) != MIN_TRIGGER_VALUE ? adjustedValueOf(-controller2.rightTrigger()) : DEFAULT_VELOCITY_CHANGE) + ");");
            statements.add("robot.setLeftServoPosition(" + (foundationArmsToggledOut ? -1 : 0) + ");");
            statements.add("robot.setRightServoPosition(" + (foundationArmsToggledOut ? 1 : 0) + ");");
            statements.add("robot.setPitchPosition(" + Range.scale(pitchPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX) + ");");
            statements.add("robot.setGripperPosition(" + Range.scale(gripperPosition, ARTIFICIAL_SERVO_MIN, ARTIFICIAL_SERVO_MAX, ACTUAL_SERVO_MIN, ACTUAL_SERVO_MAX) + ");");
        }
        return statements;
    }

    @Override
    public void setOnStateChangedCallback(Runnable callback) {
        onStateChanged = callback;
    }

    private void initTelemetryData(List<ObjectTriplet<Observable, Field, Object>> observableFields, List<ObjectTriplet<Observable, Method, Object>> observableMethods, HashMap<String, Telemetry.Item> map) {
        observableFields.forEach((ObjectTriplet<Observable, Field, Object> objectTriplet) -> {
            objectTriplet.getB().setAccessible(true);
            try {
                map.put(objectTriplet.getA().key(), telemetry.addData(objectTriplet.getA().key(), objectTriplet.getB().get(objectTriplet.getC())));
            } catch (IllegalAccessException ignored) {}
        });
        observableMethods.forEach((ObjectTriplet<Observable, Method, Object> objectTriplet) -> {
            objectTriplet.getB().setAccessible(true);
            try {
                map.put((objectTriplet.getC() instanceof Controller ? ((Controller) objectTriplet.getC()).getName() : "") + objectTriplet.getA().key(), telemetry.addData((objectTriplet.getC() instanceof Controller ? ((Controller) objectTriplet.getC()).getName() : "") + objectTriplet.getA().key(), objectTriplet.getB().invoke(objectTriplet.getC())));
            } catch (IllegalAccessException | InvocationTargetException ignored) {}
        });
    }

    private void updateTelemetryData(List<ObjectTriplet<Observable, Field, Object>> observableFields, List<ObjectTriplet<Observable, Method, Object>> observableMethods, HashMap<String, Telemetry.Item> map) {
        observableFields.forEach((ObjectTriplet<Observable, Field, Object> objectTriplet) -> Optional.ofNullable(map.get(objectTriplet.getA().key())).ifPresent((Telemetry.Item item) -> {
            try {
                item.setValue(objectTriplet.getB().get(objectTriplet.getC()));
            } catch (IllegalAccessException ignored) {}
        }));

        observableMethods.forEach((ObjectTriplet<Observable, Method, Object> objectTriplet) -> Optional.ofNullable(map.get((objectTriplet.getC() instanceof Controller ? ((Controller) objectTriplet.getC()).getName() : "") + objectTriplet.getA().key())).ifPresent((Telemetry.Item item) -> {
            try {
                item.setValue(objectTriplet.getB().invoke(objectTriplet.getC()));
            } catch (IllegalAccessException | InvocationTargetException ignored) {}
        }));
    }

    private <B> List<ObjectTriplet<Observable, B, Object>> getObservables(B[] fieldsOrMethods, Object invokee) {
        List<ObjectTriplet<Observable, B, Object>> observables = new LinkedList<>();
        for (B fieldOrMethod : fieldsOrMethods) {
            if (fieldOrMethod instanceof Field) {
                if (((Field) fieldOrMethod).isAnnotationPresent(Observable.class)) {
                    observables.add(new ObjectTriplet<>(((Field) fieldOrMethod).getAnnotation(Observable.class), fieldOrMethod, invokee));
                }
            }
            if (fieldOrMethod instanceof Method) {
                if (((Method) fieldOrMethod).isAnnotationPresent(Observable.class)) {
                    observables.add(new ObjectTriplet<>(((Method) fieldOrMethod).getAnnotation(Observable.class), fieldOrMethod, invokee));
                }
            }
        }
        return observables;
    }

    private double adjustedValueOf(double gamepadValue) {
        return gamepadValue * gamepadValue * gamepadValue;
    }
    
    private void clearTelemetry() {
        telemetry.clearAll();
        telemetryItems.clear();
    }
}