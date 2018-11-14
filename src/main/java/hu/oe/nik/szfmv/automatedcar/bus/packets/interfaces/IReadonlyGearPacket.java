package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

import hu.oe.nik.szfmv.common.enums.Gear;

public interface IReadonlyGearPacket {
    /**
     * Gets the value of the current gear.
     *
     * @return the value of the current gear
     */
    Gear getCurrentGear();
}
