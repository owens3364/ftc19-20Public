package org.firstinspires.ftc.teamcode.control.robots;

import com.qualcomm.robotcore.hardware.HardwareMap;

public interface DependencySupplier {
    HardwareMap getHardwareMap();
    void resolveDependencies();
    void resolveDependencies(HardwareMap map);
}
