package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.DashboardPacket;

public class DashboardManager extends SystemComponent {
    private final VirtualFunctionBus virtualFunctionBus;

    private DashboardPacket dashboardPacket;

    public DashboardManager(VirtualFunctionBus virtualFunctionBus) {
        super(virtualFunctionBus);

        this.virtualFunctionBus = virtualFunctionBus;
        dashboardPacket = new DashboardPacket();

    }

    public DashboardPacket getDashboardPacket() {
        return dashboardPacket;
    }

    @Override
    public void loop() {
        dashboardPacket.setGasPedalPosition(virtualFunctionBus.gasPedalPacket.getPedalPosition());
        dashboardPacket.setBrakePedalPosition(virtualFunctionBus.brakePedalPacket.getPedalPosition());
        dashboardPacket.setCurrentGear(virtualFunctionBus.gearPacket.getCurrentGear());
        dashboardPacket.setIndicatorDirection(virtualFunctionBus.indicationPacket.getIndicatorDirection());
        dashboardPacket.setSteeringWheelValue(virtualFunctionBus.steeringWheelPacket.getSteeringWheelPosition());
    }
}