package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IReadonlyPedalPacket {
    /**
     * Gives access to the gas pedal's state
     *
     * @return a value between 0 and 100
     */
    int getGasPedalPosition();

    /**
     * Gives access to the brake pedal's state
     *
     * @return a value between 0 and 100
     */
    int getBrakePedalPosition();
}
