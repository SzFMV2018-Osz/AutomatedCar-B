package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IGraduallyChangeable;

import java.time.Clock;

/**
 * This class is responsible for the determination of the changeable variable's current value
 */
public class GraduallyChangeable implements IGraduallyChangeable {

    private static Clock clock = Clock.systemUTC();

    private int from;
    private int to;
    private int milliseconds;

    private long startedAt;
    private boolean isDecreasing;

    /**
     * Changes the clock which is used by the instances to get the current time.
     *
     * @param c - clock object
     */
    public static void setClock(Clock c) {
        clock = c;
    }

    @Override
    public void startNew(int from, int to, int milliseconds) {
        this.from = from;
        this.to = to;
        this.milliseconds = milliseconds;
        this.isDecreasing = isDecreasing(from, to);

        this.startedAt = clock.millis();
    }

    @Override
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
        if (milliseconds == 0) {
            return getRange(from, to);
        }
        return (getRange(from, to) * getConvertedElapsedTime()) / milliseconds;
    }
}