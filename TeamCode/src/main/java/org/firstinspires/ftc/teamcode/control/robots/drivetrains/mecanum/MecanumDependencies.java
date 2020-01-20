package org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDependencies implements MecanumDependenciesI {
    private static final String FRONT_LEFT_DRIVE_NAME = "FL";
    private static final String FRONT_RIGHT_DRIVE_NAME = "FR";
    private static final String REAR_LEFT_DRIVE_NAME = "RL";
    private static final String REAR_RIGHT_DRIVE_NAME = "RR";

    private static final DcMotorSimple.Direction FRONT_LEFT_DRIVE_DIRECTION = DcMotorSimple.Direction.FORWARD;
    private static final DcMotorSimple.Direction FRONT_RIGHT_DRIVE_DIRECTION = DcMotorSimple.Direction.REVERSE;
    private static final DcMotorSimple.Direction REAR_LEFT_DRIVE_DIRECTION = DcMotorSimple.Direction.FORWARD;
    private static final DcMotorSimple.Direction REAR_RIGHT_DRIVE_DIRECTION = DcMotorSimple.Direction.REVERSE;

    private String frontLeftDriveName;
    private String frontRightDriveName;
    private String rearLeftDriveName;
    private String rearRightDriveName;

    private DcMotorSimple.Direction frontLeftDriveDirection;
    private DcMotorSimple.Direction frontRightDriveDirection;
    private DcMotorSimple.Direction rearLeftDriveDirection;
    private DcMotorSimple.Direction rearRightDriveDirection;

    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor rearLeftDrive;
    private DcMotor rearRightDrive;

    private HardwareMap hardwareMap;

    public MecanumDependencies setFrontLeftDriveName(String name) {
        frontLeftDriveName = name;
        return this;
    }

    public MecanumDependencies setFrontRightDriveName(String name) {
        frontRightDriveName = name;
        return this;
    }

    public MecanumDependencies setRearLeftDriveName(String name) {
        rearLeftDriveName = name;
        return this;
    }

    public MecanumDependencies setRearRightDriveName(String name) {
        rearRightDriveName = name;
        return this;
    }

    public MecanumDependencies setFrontLeftDriveDirection(DcMotorSimple.Direction direction) {
        frontLeftDriveDirection = direction;
        return this;
    }

    public MecanumDependencies setFrontRightDriveDirection(DcMotorSimple.Direction direction) {
        frontRightDriveDirection = direction;
        return this;
    }

    public MecanumDependencies setRearLeftDriveDirection(DcMotorSimple.Direction direction) {
        rearLeftDriveDirection = direction;
        return this;
    }

    public MecanumDependencies setRearRightDriveDirection(DcMotorSimple.Direction direction) {
        rearRightDriveDirection = direction;
        return this;
    }

    public MecanumDependencies setFrontLeftDrive(DcMotor motor) {
        frontLeftDrive = motor;
        return this;
    }

    public MecanumDependencies setFrontRightDrive(DcMotor motor) {
        frontRightDrive = motor;
        return this;
    }

    public MecanumDependencies setRearLeftDrive(DcMotor motor) {
        rearLeftDrive = motor;
        return this;
    }

    public MecanumDependencies setRearRightDrive(DcMotor motor) {
        rearRightDrive = motor;
        return this;
    }

    public MecanumDependencies setHardwareMap(HardwareMap map) {
        hardwareMap = map;
        return this;
    }

    @Override
    public String getFrontLeftDriveName() {
        return frontLeftDriveName;
    }

    @Override
    public String getFrontRightDriveName() {
        return frontRightDriveName;
    }

    @Override
    public String getRearLeftDriveName() {
        return rearLeftDriveName;
    }

    @Override
    public String getRearRightDriveName() {
        return rearRightDriveName;
    }

    @Override
    public DcMotorSimple.Direction getFrontLeftDriveDirection() {
        return frontLeftDriveDirection;
    }

    @Override
    public DcMotorSimple.Direction getFrontRightDriveDirection() {
        return frontRightDriveDirection;
    }

    @Override
    public DcMotorSimple.Direction getRearLeftDriveDirection() {
        return rearLeftDriveDirection;
    }

    @Override
    public DcMotorSimple.Direction getRearRightDriveDirection() {
        return rearRightDriveDirection;
    }

    @Override
    public DcMotor getFrontLeftDrive() {
        return frontLeftDrive;
    }

    @Override
    public DcMotor getFrontRightDrive() {
        return frontRightDrive;
    }

    @Override
    public DcMotor getRearLeftDrive() {
        return rearLeftDrive;
    }

    @Override
    public DcMotor getRearRightDrive() {
        return rearRightDrive;
    }

    @Override
    public HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    @Override
    public void resolveDependencies() {
        resolveMotors();
        resolveDirections();
    }

    @Override
    public void resolveDependencies(HardwareMap map) {
        hardwareMap = map;
        resolveDependencies();
    }

    private void resolveMotors() {
        if (frontLeftDrive == null) {
            if (frontLeftDriveName != null) {
                frontLeftDrive = hardwareMap.dcMotor.get(frontLeftDriveName);
            } else {
                frontLeftDrive = hardwareMap.dcMotor.get(FRONT_LEFT_DRIVE_NAME);
                frontLeftDriveName = FRONT_LEFT_DRIVE_NAME;
            }
        }

        if (frontRightDrive == null) {
            if (frontRightDriveName != null) {
                frontRightDrive = hardwareMap.dcMotor.get(frontRightDriveName);
            } else {
                frontRightDrive = hardwareMap.dcMotor.get(FRONT_RIGHT_DRIVE_NAME);
                frontRightDriveName = FRONT_RIGHT_DRIVE_NAME;
            }
        }

        if (rearLeftDrive == null) {
            if (rearLeftDriveName != null) {
                rearLeftDrive = hardwareMap.dcMotor.get(rearLeftDriveName);
            } else {
                rearLeftDrive = hardwareMap.dcMotor.get(REAR_LEFT_DRIVE_NAME);
                rearLeftDriveName = REAR_LEFT_DRIVE_NAME;
            }
        }

        if (rearRightDrive == null) {
            if (rearRightDriveName != null) {
                rearRightDrive = hardwareMap.dcMotor.get(rearRightDriveName);
            } else {
                rearRightDrive = hardwareMap.dcMotor.get(REAR_RIGHT_DRIVE_NAME);
                rearRightDriveName = REAR_RIGHT_DRIVE_NAME;
            }
        }
    }

    private void resolveDirections() {
        if (frontLeftDriveDirection == null) {
            frontLeftDriveDirection = FRONT_LEFT_DRIVE_DIRECTION;
        }
        if (frontRightDriveDirection == null) {
            frontRightDriveDirection = FRONT_RIGHT_DRIVE_DIRECTION;
        }
        if (rearLeftDriveDirection == null) {
            rearLeftDriveDirection = REAR_LEFT_DRIVE_DIRECTION;
        }
        if (rearRightDriveDirection == null) {
            rearRightDriveDirection = REAR_RIGHT_DRIVE_DIRECTION;
        }

        frontLeftDrive.setDirection(frontLeftDriveDirection);
        frontRightDrive.setDirection(frontRightDriveDirection);
        rearLeftDrive.setDirection(rearLeftDriveDirection);
        rearRightDrive.setDirection(rearRightDriveDirection);
    }
}
