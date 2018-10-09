package hu.oe.nik.szfmv.automatedcar.bus.packets;

import java.time.Clock;

/**
 * This class is responsible for the determination of the changeable variable's current value
 */
public class GraduallyChangeable {

    private static Clock clock = Clock.systemUTC();

    private int from;
    private int to;
    private int milliseconds;

    private long startedAt;
    private boolean isDecreasing;

    /**
     * Initializes a new instance for a task with following parameters
     *
     * @param from         - the starting value
     * @param to           - the target value
     * @param milliseconds - the time of the transition
     */
    public GraduallyChangeable(int from, int to, int milliseconds) {
        this.from = from;
        this.to = to;
        this.milliseconds = milliseconds;

        this.isDecreasing = isDecreasing(from, to);
    }

    /**
     * Initializes a new instance for a task with following parameters and starts it.
     *
     * @param from         - the starting value
     * @param to           - the target value
     * @param milliseconds - the time of the transition
     * @return The instance which has been created by this method.
     */
    public static GraduallyChangeable startNew(int from, int to, int milliseconds) {

        GraduallyChangeable gc = new GraduallyChangeable(from, to, milliseconds);
        gc.start();

        return gc;
    }

    /**
     * Changes the clock which is used by the instances to get the current time.
     *
     * @param c - clock object
     */
    public static void setClock(Clock c) {
        clock = c;
    }

    /**
     * Start the gradual changing task
     */
    public void start() {
        this.startedAt = clock.millis();
    }

    /**
     * Give access to the value of the gradually changeable variable
     *
     * @return the current state of the variable in the time of the method call
     */
    public int getCurrentValue() {
        if (isDecreasing) {
            return from - determineChange();
        }

        return from + determineChange();
    }

    private int getConvertedElapsedTime() {
        int elapsedTime = (int) (clock.millis() - startedAt);

        return Math.min(elapsedTime, milliseconds);
    }

    private int getRange(int a, int b) {
        return Math.max(a, b) - Math.min(a, b);
    }

    private boolean isDecreasing(int from, int to) {
        return to < from;

    }

    private int determineChange() {
        return (getRange(from, to) * getConvertedElapsedTime()) / milliseconds;
    }
}