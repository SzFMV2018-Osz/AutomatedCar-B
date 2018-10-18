package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IReadonlyIndicationPacket {

    /*
     * @return -1 if the direction is left
     * @return 0 if the indicator is inactive
     * @return +1 if the direction is right
     */
    int getIndicatorDirection();
}
