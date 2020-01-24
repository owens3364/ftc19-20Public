package org.firstinspires.ftc.teamcode.control.robots.drivetrains.standard;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.control.robots.Refreshable;
import org.firstinspires.ftc.teamcode.control.robots.Wheel;
import org.firstinspires.ftc.teamcode.control.robots.WheelI;
import org.firstinspires.ftc.teamcode.control.robots.WheelPosition;

public class StandardDrive implements StandardDriveI {
    private static final int MOTOR_COUNT = 2;

    private final StandardDrivetrainI driveTrain;
    private final StandardDependenciesI dependencies;

    public StandardDrive(HardwareMap map) {
        dependencies = new StandardDependencies();
        dependencies.resolveDependencies(map);
        driveTrain = genDrivetrain(dependencies);
    }

    public StandardDrive(HardwareMap map, StandardDependenciesI dependencies) {
        this.dependencies = dependencies;
        dependencies.resolveDependencies(map);
        driveTrain = genDrivetrain(dependencies);
    }

    private StandardDrivetrainI genDrivetrain(StandardDependenciesI dependencies) {
        return new StandardDrivetrain(Wheel.fromArr(dependencies.getLeftDrives(), WheelPosition.arrOf(WheelPosition.LEFT, dependencies.getLeftDrives().length)), Wheel.fromArr(dependencies.getRightDrives(), WheelPosition.arrOf(WheelPosition.RIGHT, dependencies.getRightDrives().length)));
    }

    @Override
    public StandardDrivetrainI driveBySticks(StandardDriveInputI input) {
        if (input.isTankMode()) {
            for (WheelI wheel : driveTrain.getLeft()) {
                wheel.getMotor().setPower(input.getLeft());
            }
            for (WheelI wheel : driveTrain.getRight()) {
                wheel.getMotor().setPower(input.getRight());
            }
        } else {
            for (WheelI wheel : driveTrain.getLeft()) {
                wheel.getMotor().setPower(Range.clip(input.getY() + 2 * input.getTurn(), -1.0, 1.0));
            }
            for (WheelI wheel : driveTrain.getRight()) {
                wheel.getMotor().setPower(Range.clip(input.getY() - 2 * input.getTurn(), -1.0, 1.0));
            }
        }
        return driveTrain;
    }

    @Override
    public StandardDrivetrainI all(double power) {
        for (WheelI wheel : driveTrain.getLeft()) {
            wheel.getMotor().setPower(power);
        }
        for (WheelI wheel : driveTrain.getRight()) {
            wheel.getMotor().setPower(power);
        }
        return driveTrain;
    }

    @Override
    public StandardDrivetrainI only(double ... powers) {
        if (powers != null) {
            switch (powers.length) {
                case 2:
                    for (WheelI wheel : driveTrain.getRight()) {
                        wheel.getMotor().setPower(powers[1]);
                    }
                case 1:
                    for (WheelI wheel : driveTrain.getLeft()) {
                        wheel.getMotor().setPower(powers[0]);
                    }
                default: break;
            }
        }
        return driveTrain;
    }

    @Override
    public Refreshable refresh() {
        dependencies.refresh();
        return this;
    }
}
















