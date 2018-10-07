package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

import hu.oe.nik.szfmv.common.enums.Gear;

public interface IReadonlyGearPacket {
    Gear getCurrentGear();
}
