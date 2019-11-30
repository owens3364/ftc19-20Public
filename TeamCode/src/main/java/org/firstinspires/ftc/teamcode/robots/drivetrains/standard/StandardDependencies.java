package org.firstinspires.ftc.teamcode.robots.drivetrains.standard;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.LinkedList;

public class StandardDependencies implements StandardDependenciesI {
    private static final String LEFT_PREFIX = "L";
    private static final String RIGHT_PREFIX = "R";

    private static String getDefaultLeftName(int index) {
        return LEFT_PREFIX + index;
    }

    private static String getDefaultRightName(int index) {
        return RIGHT_PREFIX + index;
    }

    private static final DcMotorSimple.Direction LEFT_DIRECTION = DcMotorSimple.Direction.REVERSE;
    private static final DcMotorSimple.Direction RIGHT_DIRECTION = DcMotorSimple.Direction.FORWARD;

    private String[] leftDriveNames;
    private String[] rightDriveNames;

    private DcMotorSimple.Direction[] leftDriveDirections;
    private DcMotorSimple.Direction[] rightDriveDirections;

    private DcMotor[] leftDrives;
    private DcMotor[] rightDrives;

    private HardwareMap hardwareMap;

    public StandardDependencies setLeftDriveNames(String ... leftDriveNames) {
        this.leftDriveNames = leftDriveNames;
        return this;
    }

    public StandardDependencies setRightDriveNames(String ... rightDriveNames) {
        this.rightDriveNames = rightDriveNames;
        return this;
    }

    public StandardDependencies setLeftDriveDirections(DcMotorSimple.Direction ... leftDriveDirections) {
        this.leftDriveDirections = leftDriveDirections;
        return this;
    }

    public StandardDependencies setRightDriveDirections(DcMotorSimple.Direction ... rightDriveDirections) {
        this.rightDriveDirections = rightDriveDirections;
        return this;
    }

    public StandardDependencies setLeftDrives(DcMotor ... leftDrives) {
        this.leftDrives = leftDrives;
        return this;
    }

    public StandardDependencies setRightDrives(DcMotor ... rightDrives) {
        this.rightDrives = rightDrives;
        return this;
    }

    public StandardDependencies setHardwareMap(HardwareMap map) {
        hardwareMap = map;
        return this;
    }

    @Override
    public String[] getLeftDriveNames() {
        return leftDriveNames;
    }

    @Override
    public String[] getRightDriveNames() {
        return rightDriveNames;
    }

    @Override
    public DcMotorSimple.Direction[] getLeftDriveDirections() {
        return leftDriveDirections;
    }

    @Override
    public DcMotorSimple.Direction[] getRightDriveDirections() {
        return rightDriveDirections;
    }

    @Override
    public DcMotor[] getLeftDrives() {
        return leftDrives;
    }

    @Override
    public DcMotor[] getRightDrives() {
        return rightDrives;
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
        if (leftDrives == null) {
            if (leftDriveNames != null) {
                leftDrives = new DcMotor[leftDriveNames.length];
                for (int i = 0; i < leftDriveNames.length; i++) {
                    leftDrives[i] = hardwareMap.dcMotor.get(leftDriveNames[i] != null ? leftDriveNames[i] : getDefaultLeftName(i));
                }
            } else {
                String leftDriveName = getDefaultLeftName(0);
                leftDrives = new DcMotor[] {hardwareMap.dcMotor.get(leftDriveName)};
                leftDriveNames = new String[] {leftDriveName};
            }
        } else {
            LinkedList<DcMotor> drives = new LinkedList<>();
            for (DcMotor drive : leftDrives) {
                if (drive != null) drives.add(drive);
            }
            leftDrives = new DcMotor[0];
            leftDrives = drives.toArray(leftDrives);
        }

        if (rightDrives == null) {
            if (rightDriveNames != null) {
                rightDrives = new DcMotor[rightDriveNames.length];
                for (int i = 0; i < rightDriveNames.length; i++) {
                    rightDrives[i] = hardwareMap.dcMotor.get(rightDriveNames[i] != null ? rightDriveNames[i] : getDefaultRightName(i));
                }
            } else {
                String rightDriveName = getDefaultRightName(0);
                rightDrives = new DcMotor[] {hardwareMap.dcMotor.get(rightDriveName)};
                rightDriveNames = new String[] {rightDriveName};
            }
        } else {
            LinkedList<DcMotor> drives = new LinkedList<>();
            for (DcMotor drive : rightDrives) {
                if (drive != null) drives.add(drive);
            }
            rightDrives = new DcMotor[0];
            rightDrives = drives.toArray(rightDrives);
        }
    }

    private void resolveDirections() {
        if (leftDriveDirections != null) {
            LinkedList<DcMotorSimple.Direction> driveDirections = new LinkedList<>();
            for (DcMotorSimple.Direction driveDirection : leftDriveDirections) {
                if (driveDirection != null) {
                    driveDirections.add(driveDirection);
                } else {
                    driveDirections.add(DcMotorSimple.Direction.REVERSE);
                }
            }
            leftDriveDirections = new DcMotorSimple.Direction[0];
            leftDriveDirections = driveDirections.toArray(leftDriveDirections);
        } else {
            leftDriveDirections = new DcMotorSimple.Direction[] {LEFT_DIRECTION};
        }

        if (rightDriveDirections != null) {
            LinkedList<DcMotorSimple.Direction> driveDirections = new LinkedList<>();
            for (DcMotorSimple.Direction driveDirection : rightDriveDirections) {
                if (driveDirection != null) {
                    driveDirections.add(driveDirection);
                } else {
                    driveDirections.add(DcMotorSimple.Direction.FORWARD);
                }
            }
            rightDriveDirections = new DcMotorSimple.Direction[0];
            rightDriveDirections = driveDirections.toArray(rightDriveDirections);
        } else {
            rightDriveDirections = new DcMotorSimple.Direction[] {RIGHT_DIRECTION};
        }

        if (leftDriveDirections.length == leftDrives.length) {
            for (int i = 0; i < leftDriveDirections.length; i++) {
                leftDrives[i].setDirection(leftDriveDirections[i]);
            }
        } else {
            for (int i = 0; i < Math.min(leftDrives.length, leftDriveDirections.length); i++) {
                leftDrives[i].setDirection(leftDriveDirections[i]);
            }
        }

        if (rightDriveDirections.length == rightDrives.length) {
            for (int i = 0; i < rightDriveDirections.length; i++) {
                rightDrives[i].setDirection(rightDriveDirections[i]);
            }
        } else {
            for (int i = 0; i < Math.min(rightDrives.length, rightDriveDirections.length); i++) {
                rightDrives[i].setDirection(rightDriveDirections[i]);
            }
        }
    }
}