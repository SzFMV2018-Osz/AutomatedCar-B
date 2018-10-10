package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

import hu.oe.nik.szfmv.common.enums.Gear;

public interface IReadonlyGearPacket {
    /**
     * Gives access to the gear's state
     *
     * @return the current gear
     */
    Gear getCurrentGear();
}
