package org.firstinspires.ftc.teamcode.control.robots.drivetrains.mecanum;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.control.robots.Wheel;
import org.firstinspires.ftc.teamcode.control.robots.WheelPosition;

public class MecanumDrive implements MecanumDriveI {
    private final MecanumDrivetrainI driveTrain;

    public MecanumDrive(HardwareMap map) {
        MecanumDependenciesI dependencies = new MecanumDependencies();
        dependencies.resolveDependencies(map);
        driveTrain = genDrivetrain(dependencies);
    }

    public MecanumDrive(HardwareMap map, MecanumDependenciesI dependencies) {
        dependencies.resolveDependencies(map);
        driveTrain = genDrivetrain(dependencies);
    }

    private MecanumDrivetrainI genDrivetrain(MecanumDependenciesI dependencies) {
        return new MecanumDrivetrain(new Wheel(WheelPosition.FRONT_LEFT, dependencies.getFrontLeftDrive()), new Wheel(WheelPosition.FRONT_RIGHT, dependencies.getFrontRightDrive()), new Wheel(WheelPosition.REAR_LEFT, dependencies.getRearLeftDrive()), new Wheel(WheelPosition.REAR_RIGHT, dependencies.getRearRightDrive()));
    }

    @Override
    public MecanumDrivetrainI driveBySticks(MecanumDriveInputI input) {
        double targetPoint = Math.hypot(-input.getX(), input.getY());
        double targetAngle = Math.atan2(input.getY(), -input.getX()) - Math.PI / 4;
        driveTrain.getFrontLeft().getMotor().setPower(targetPoint * Math.cos(targetAngle) + input.getTurn());
        driveTrain.getFrontRight().getMotor().setPower(targetPoint * Math.sin(targetAngle) - input.getTurn());
        driveTrain.getRearLeft().getMotor().setPower(targetPoint * Math.sin(targetAngle) + input.getTurn());
        driveTrain.getRearRight().getMotor().setPower(targetPoint * Math.cos(targetAngle) - input.getTurn());
        return driveTrain;
    }

    @Override
    public MecanumDrivetrainI all(double power) {
        driveTrain.getFrontLeft().getMotor().setPower(power);
        driveTrain.getFrontRight().getMotor().setPower(power);
        driveTrain.getRearLeft().getMotor().setPower(power);
        driveTrain.getRearRight().getMotor().setPower(power);
        return driveTrain;
    }


    @Override
    public MecanumDrivetrainI only(double ... powers) {
        if (powers != null) {
            switch (powers.length) {
                case 4: driveTrain.getRearRight().getMotor().setPower(powers[3]);
                case 3: driveTrain.getRearLeft().getMotor().setPower(powers[2]);
                case 2: driveTrain.getFrontRight().getMotor().setPower(powers[1]);
                case 1: driveTrain.getFrontLeft().getMotor().setPower(powers[0]);
                default: break;
            }
        }
        return driveTrain;
    }

    @Override
    public MecanumDrivetrainI getDrivetrain() {
        return driveTrain;
    }
}
