package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.angularSlider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider.SliderArmDependencies;

import java.util.LinkedList;

public class AngularSliderArmDependencies extends SliderArmDependencies implements AngularSliderArmDependenciesI {
    private static final String ANGULAR_DRIVE_PREFIX = "A";

    private static String getDefaultAngularName(int index) {
        return ANGULAR_DRIVE_PREFIX + index;
    }

    private static final DcMotorSimple.Direction ANGULAR_DRIVE_DIRECTION = DcMotorSimple.Direction.FORWARD;

    private String[] angularDriveNames;

    private DcMotorSimple.Direction[] angularDriveDirections;

    private DcMotor[] angularDrives;

    private HardwareMap hardwareMap;

    public AngularSliderArmDependencies setAngularDriveNames(String ... names) {
        angularDriveNames = names;
        return this;
    }

    public AngularSliderArmDependencies setAngularDriveDirection(DcMotorSimple.Direction ... directions) {
        angularDriveDirections = directions;
        return this;
    }

    public AngularSliderArmDependencies setAngularDrive(DcMotor ... motors) {
        angularDrives = motors;
        return this;
    }

    @Override
    public String[] getAngularDriveNames() {
        return angularDriveNames;
    }

    @Override
    public DcMotorSimple.Direction[] getAngularDriveDirections() {
        return angularDriveDirections;
    }

    @Override
    public DcMotor[] getAngularDrives() {
        return angularDrives;
    }

    @Override
    public HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    @Override
    public void resolveDependencies() {
        super.resolveDependencies();
        resolveMotors();
        resolveDirections();
        reset();
    }

    @Override
    public void resolveDependencies(HardwareMap map) {
        super.resolveDependencies(map);
        hardwareMap = map;
        resolveDependencies();
    }

    private void resolveMotors() {
        if (angularDrives == null) {
            if (angularDriveNames != null) {
                angularDrives = new DcMotor[angularDriveNames.length];
                for (int i = 0; i < angularDriveNames.length; i++) {
                    angularDrives[i] = hardwareMap.dcMotor.get(angularDriveNames[i] != null ? angularDriveNames[i] : getDefaultAngularName(i));
                }
            } else {
                String angularDriveName = getDefaultAngularName(0);
                angularDrives = new DcMotor[] {hardwareMap.dcMotor.get(angularDriveName)};
                angularDriveNames = new String[] {angularDriveName};
            }
        } else {
            LinkedList<DcMotor> drives = new LinkedList<>();
            for (DcMotor drive : angularDrives) {
                if (drive != null) drives.add(drive);
            }
            angularDrives = new DcMotor[0];
            angularDrives = drives.toArray(angularDrives);
        }
    }

    private void resolveDirections() {
        if (angularDriveDirections != null) {
            LinkedList<DcMotorSimple.Direction> driveDirections = new LinkedList<>();
            for (DcMotorSimple.Direction driveDirection : angularDriveDirections) {
                if (driveDirection != null) {
                    driveDirections.add(driveDirection);
                } else {
                    driveDirections.add(ANGULAR_DRIVE_DIRECTION);
                }
            }
            angularDriveDirections = new DcMotorSimple.Direction[0];
            angularDriveDirections = driveDirections.toArray(angularDriveDirections);
        } else {
            angularDriveDirections = new DcMotorSimple.Direction[] {ANGULAR_DRIVE_DIRECTION};
        }
        if (angularDriveDirections.length == angularDrives.length) {
            for (int i = 0; i < angularDriveDirections.length; i++) {
                angularDrives[i].setDirection(angularDriveDirections[i]);
            }
        } else {
            for (int i = 0; i < Math.min(angularDrives.length, angularDriveDirections.length); i++) {
                angularDrives[i].setDirection(angularDriveDirections[i]);
            }
        }
    }

    private void reset() {
        for (DcMotor angularDrive : angularDrives) {
            angularDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            angularDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
