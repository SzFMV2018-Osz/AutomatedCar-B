package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.DashboardPacket;

public class DashboardManager extends SystemComponent {
    private DashboardPacket dashboardPacket;

    /**
     * Constructor for the dashboard manager
     *
     * @param virtualFunctionBus - the bus object to link with
     */
    public DashboardManager(VirtualFunctionBus virtualFunctionBus) {
        super(virtualFunctionBus);

        dashboardPacket = new DashboardPacket();

        virtualFunctionBus.dashboardPacket = this.dashboardPacket;
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
        dashboardPacket.setRpm(virtualFunctionBus.powertrainPacket.getRpm());
        dashboardPacket.setSpeed(virtualFunctionBus.velocityPacket.getSpeed());
    }

    /**
     * Fetches the automated car's x and y coordinates to be displayed on the dashboard
     *
     * @param x - the automated car's x coordinate
     * @param y - the automated car's y coordinate
     */
    public void actualisePosition(int x, int y) {
        dashboardPacket.setAutomatedCarX(x);
        dashboardPacket.setAutomatedCarY(y);
    }
}