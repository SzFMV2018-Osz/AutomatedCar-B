package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IReadonlyIndicationPacket {

    /**
     * Gives access to the indicator's state
     *
     * @return -1 if the direction is left, 0 if the indicator is inactive or +1 if the direction is right
     */
    int getIndicatorDirection();
}
