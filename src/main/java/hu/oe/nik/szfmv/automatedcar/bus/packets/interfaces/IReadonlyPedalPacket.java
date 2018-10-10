package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IReadonlyPedalPacket {
    /**
      *
      * @return a value between 0 and 100
      */
    int getPedalPosition();
}
