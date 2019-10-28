package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

public interface DependencySupplier {
    HardwareMap getHardwareMap();
    void resolveDependencies();
    void resolveDependencies(HardwareMap map);
}
