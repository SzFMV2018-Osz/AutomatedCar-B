package hu.oe.nik.szfmv.automatedcar.bus.packets.sample;

public interface ReadOnlySamplePacket {

    /**
     * Gets the position of the gas pedal.
     * @return the position of the gas pedal
     */
    int getGaspedalPosition();
}
