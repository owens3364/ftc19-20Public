package org.firstinspires.ftc.teamcode.control.robots;

public interface Initializer extends Comparable, Refreshable {
    Byte getPriority();
    Runnable getInitializer();
}
