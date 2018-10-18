package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IReadonlyIndicationPacket {

    /**
     * @return -1 if the direction is left
     * 0 if the indicator is inactive
     * +1 if the direction is right
     */
    int getIndicatorDirection();
}
