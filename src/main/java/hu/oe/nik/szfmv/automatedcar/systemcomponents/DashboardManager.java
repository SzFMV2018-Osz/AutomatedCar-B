package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.DashboardPacket;

public class DashboardManager extends SystemComponent {
    private final DashboardPacket dashboardPacket;

    public DashboardManager(VirtualFunctionBus virtualFunctionBus) {
        super(virtualFunctionBus);

        dashboardPacket = new DashboardPacket();

        virtualFunctionBus.dashboardPacket = dashboardPacket;
    }

    @Override
    public void loop() {

    }
}