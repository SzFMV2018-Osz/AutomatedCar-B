package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IGraduallyChangeable {

    /**
     * Starts a new measurement.
     *
     * @param from         - starting time
     * @param to           finishing time
     * @param milliseconds - unit of measurement
     */
    void startNew(int from, int to, int milliseconds);

    /**
     * Returns the current value.
     * @return the current value
     */
    int getCurrentValue();
}
