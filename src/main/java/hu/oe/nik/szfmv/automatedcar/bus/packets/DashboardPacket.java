package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadOnlyDashboardPacket;

public class DashboardPacket implements IReadOnlyDashboardPacket {
    private int automatedCarX;
    private int automatedCarY;
    private int steeringWheelValue;

    public DashboardPacket(){
        this.automatedCarX = 0;
        this.automatedCarY = 0;
        this.steeringWheelValue = 0;
    }

    @Override
    public int getAutomatedCarX() {
        return automatedCarX;
    }

    @Override
    public int getAutomatedCarY() {
        return automatedCarY;
    }

    @Override
    public int getSteeringWheelValue() {
        return steeringWheelValue;
    }
}