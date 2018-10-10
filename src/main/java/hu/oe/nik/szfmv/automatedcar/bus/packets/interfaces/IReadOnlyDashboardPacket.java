package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

public interface IReadOnlyDashboardPacket {
    public int getAutomatedCarX();
    public int getAutomatedCarY();
    public int getSteeringWheelValue();
}