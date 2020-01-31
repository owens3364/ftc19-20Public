package org.firstinspires.ftc.teamcode.control.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

public interface DependencySupplier {
    HardwareMap getHardwareMap();
    DependencySupplier setHardwareMap(HardwareMap map);
    void resolveDependencies();
    void resolveDependencies(HardwareMap map);
    int getPriority();
}
