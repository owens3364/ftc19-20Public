package org.firstinspires.ftc.teamcode.control.robots;

public class InitializerImpl implements Initializer {
    private final byte priority;
    private final Runnable initalizer;

    public InitializerImpl(byte priority, Runnable initializer) {
        this.priority = priority;
        this.initalizer = initializer;
    }

    @Override
    public Byte getPriority() {
        return priority;
    }

    @Override
    public Runnable getInitializer() {
        return initalizer;
    }

    @Override
    public int compareTo(Object other) {
        if (other instanceof Initializer) {
            return this.priority < ((Initializer) other).getPriority() ? -1 : this.priority > ((Initializer) other).getPriority() ? 1 : 0;
        }
        return -1;
    }

    @Override
    public Refreshable refresh() {
        this.initalizer.run();
        return null;
    }
}
