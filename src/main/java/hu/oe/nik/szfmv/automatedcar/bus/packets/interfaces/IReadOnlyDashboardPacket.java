package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

import hu.oe.nik.szfmv.common.enums.Gear;

public interface IReadOnlyDashboardPacket {
    int getAutomatedCarX();

    int getAutomatedCarY();

    int getSteeringWheelValue();

    Gear getCurrentGear();

    int getIndicatorDirection();

    int getGasPedalPosition();

    int getBrakePedalPosition();
}