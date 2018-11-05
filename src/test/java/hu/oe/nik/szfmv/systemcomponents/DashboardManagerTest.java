package hu.oe.nik.szfmv.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.DashboardPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.ReadonlyVelocityPacket;
import hu.oe.nik.szfmv.automatedcar.engine.TransmissionModes;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.DashboardManager;
import hu.oe.nik.szfmv.automatedcar.bus.packets.ReadonlyPowertrainPacket;
import hu.oe.nik.szfmv.common.enums.Gear;
import org.junit.Assert;
import org.junit.Test;

public class DashboardManagerTest {


    @Test
    public void Initialization() {
        VirtualFunctionBus vfs = new VirtualFunctionBus();

        DashboardManager dm = new DashboardManager(vfs);

        Assert.assertNotNull(dm);
    }

    @Test
    public void BeforeLoop() {
        VirtualFunctionBus vfs = new VirtualFunctionBusMock(50, 10,
                15, 0, Gear.D, 1500, 100.0);
        DashboardManager dm = new DashboardManager(vfs);

        DashboardPacket empty = dm.getDashboardPacket();

        Assert.assertNotNull(empty);
        Assert.assertEquals(0, empty.getAutomatedCarX());
        Assert.assertEquals(0, empty.getAutomatedCarY());
        Assert.assertEquals(0, empty.getBrakePedalPosition());
        Assert.assertEquals(0, empty.getGasPedalPosition());
        Assert.assertEquals(0, empty.getIndicatorDirection());
        Assert.assertEquals(0, empty.getSteeringWheelValue());
        Assert.assertEquals(0, empty.getRpm());
        Assert.assertEquals(0, empty.getSpeed());
        Assert.assertNull(empty.getCurrentGear());

    }

    @Test
    public void AfterLoop() {
        int gasPedal = 50;
        int brakePedal = 10;
        int steeringWheel = 15;
        int indicator = 0;
        int rpm = 2500;
        double speed = 100.0;
        Gear gear = Gear.D;
        VirtualFunctionBusMock vfs = new VirtualFunctionBusMock(gasPedal, brakePedal, steeringWheel,
                indicator, gear, rpm, speed);
        DashboardManager dm = new DashboardManager(vfs);

        dm.loop();
        DashboardPacket packet = dm.getDashboardPacket();

        Assert.assertEquals(gasPedal, packet.getGasPedalPosition());
        Assert.assertEquals(brakePedal, packet.getBrakePedalPosition());
        Assert.assertEquals(steeringWheel, packet.getSteeringWheelValue());
        Assert.assertEquals(indicator, packet.getIndicatorDirection());
        Assert.assertEquals(rpm, packet.getRpm());
        Assert.assertEquals(speed, packet.getSpeed(), 0);
        Assert.assertEquals(gear, packet.getCurrentGear());

    }

    @Test
    public void CarPosition() {
        VirtualFunctionBusMock dummy = new VirtualFunctionBusMock();
        DashboardManager dm = new DashboardManager(dummy);
        int x = 10;
        int y = 20;

        dm.actualisePosition(x, y);
        DashboardPacket packet = dm.getDashboardPacket();

        Assert.assertEquals(x, packet.getAutomatedCarX());
        Assert.assertEquals(y, packet.getAutomatedCarY());
    }


    class VirtualFunctionBusMock extends VirtualFunctionBus {

        VirtualFunctionBusMock() {

        }

        VirtualFunctionBusMock(int gasPedal, int brakePedal, int steeringWheel,
                               int indicator, Gear gear, int rpm, double speed) {

            this.steeringWheelPacket = () -> steeringWheel;

            this.indicationPacket = () -> indicator;

            this.gearPacket = () -> gear;

            this.brakePedalPacket = () -> brakePedal;

            this.gasPedalPacket = () -> gasPedal;

            this.powertrainPacket = new ReadonlyPowertrainPacket() {
                @Override
                public int getRpm() {
                    return rpm;
                }

                @Override
                public int getGear() {
                    return 0;
                }

                @Override
                public TransmissionModes getTransmissionMode() {
                    return null;
                }

                @Override
                public int getBrakePadelPosition() {
                    return 0;
                }

                @Override
                public int getThrottlePosition() {
                    return 0;
                }
            };

            this.velocityPacket = new ReadonlyVelocityPacket() {
                @Override
                public double[] getVelocity() {
                    return new double[0];
                }

                @Override
                public double getSpeed() {
                    return speed;
                }
            };
        }
    }
}
