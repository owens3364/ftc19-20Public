package org.firstinspires.ftc.teamcode.control.robots.auxiliaries.arms.slider;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.DependencySupplier;
import org.firstinspires.ftc.teamcode.control.robots.Refreshable;

import java.util.LinkedList;

public class SliderArmDependencies implements SliderArmDependenciesI {
    private static final String SLIDER_DRIVE_PREFIX = "S";
    private static final byte DEFAULT_PRIORITY = 0xA;

    private static String getDefaultSliderName(int index) {
        return SLIDER_DRIVE_PREFIX + index;
    }

    private static final DcMotorSimple.Direction SLIDER_DRIVE_DIRECTION = DcMotorSimple.Direction.FORWARD;

    private String[] sliderDriveNames;

    private DcMotorSimple.Direction[] sliderDriveDirections;

    private DcMotor[] sliderDrives;

    private HardwareMap hardwareMap;

    private byte priority = -1;

    public SliderArmDependencies setSliderDriveNames(String ... names) {
        sliderDriveNames = names;
        return this;
    }

    public SliderArmDependencies setSliderDriveDirections(DcMotorSimple.Direction ... directions) {
        sliderDriveDirections = directions;
        return this;
    }

    public SliderArmDependencies setSliderDrives(DcMotor ... motors) {
        sliderDrives = motors;
        return this;
    }

    public SliderArmDependencies setPriority(byte priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public String[] getSliderDriveNames() {
        return sliderDriveNames;
    }

    @Override
    public DcMotorSimple.Direction[] getSliderDriveDirections() {
        return sliderDriveDirections;
    }

    @Override
    public DcMotor[] getSliderDrives() {
        return sliderDrives;
    }

    @Override
    public HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    @Override
    public DependencySupplier setHardwareMap(HardwareMap map) {
        hardwareMap = map;
        return this;
    }

    @Override
    public void resolveDependencies() {
        resolveMotors();
        resolveDirections();
        reset();
    }

    @Override
    public void resolveDependencies(HardwareMap map) {
        hardwareMap = map;
        resolveDependencies();
    }

    @Override
    public int getPriority() {
        if (priority == -1) priority = DEFAULT_PRIORITY;
        return priority;
    }

    private void resolveMotors() {
        if (sliderDrives == null) {
            if (sliderDriveNames != null) {
                sliderDrives = new DcMotor[sliderDriveNames.length];
                for (int i = 0; i < sliderDriveNames.length; i++) {
                    sliderDrives[i] = hardwareMap.dcMotor.get(sliderDriveNames[i] != null ? sliderDriveNames[i] : getDefaultSliderName(i));
                }
            } else {
                String angularDriveName = getDefaultSliderName(0);
                sliderDrives = new DcMotor[] {hardwareMap.dcMotor.get(angularDriveName)};
                sliderDriveNames = new String[] {angularDriveName};
            }
        } else {
            LinkedList<DcMotor> drives = new LinkedList<>();
            for (DcMotor drive : sliderDrives) {
                if (drive != null) drives.add(drive);
            }
            sliderDrives = new DcMotor[0];
            sliderDrives = drives.toArray(sliderDrives);
        }
    }

    private void resolveDirections() {
        if (sliderDriveDirections != null) {
            LinkedList<DcMotorSimple.Direction> driveDirections = new LinkedList<>();
            for (DcMotorSimple.Direction driveDirection : sliderDriveDirections) {
                if (driveDirection != null) {
                    driveDirections.add(driveDirection);
                } else {
                    driveDirections.add(SLIDER_DRIVE_DIRECTION);
                }
            }
            sliderDriveDirections = new DcMotorSimple.Direction[0];
            sliderDriveDirections = driveDirections.toArray(sliderDriveDirections);
        } else {
            sliderDriveDirections = new DcMotorSimple.Direction[] {SLIDER_DRIVE_DIRECTION};
        }
        for (int i = 0; i < Math.min(sliderDrives.length, sliderDriveDirections.length); i++) {
            sliderDrives[i].setDirection(sliderDriveDirections[i]);
        }
    }

    private void reset() {
        for (DcMotor sliderDrive : sliderDrives) {
            sliderDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            sliderDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    @Override
    public Refreshable refresh() {
        resolveDirections();
        return this;
    }
}
