package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyGearPacket;
import hu.oe.nik.szfmv.common.enums.Gear;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GearPacketTest {

    @Test
    public void initialization(){
        IReadonlyGearPacket gearPacket = new GearPacket(new UserInputDummy());

        Gear actual = gearPacket.getCurrentGear();

        Assert.assertEquals(Gear.N, actual);
    }

    @Test
    public void subscriptionToUserInput(){
        UserInputFake fake = new UserInputFake();

        IReadonlyGearPacket gearPacket = new GearPacket(fake);

        Assert.assertNotEquals(0, fake.shiftingEventHandlers.size());
    }

    @Test
    public void snapshot(){
        GearPacket gearPacket = new GearPacket(new UserInputDummy());

        gearPacket.createSnapshot();
        gearPacket.onShifting(Gear.P);

        Assert.assertEquals(gearPacket.getCurrentGear(), Gear.N);
    }

    @Test
    public void shiftingToPark(){
        GearPacket gearPacket = new GearPacket(new UserInputDummy());

        gearPacket.onShifting(Gear.P);
        gearPacket.createSnapshot();

        Assert.assertEquals(Gear.P, gearPacket.getCurrentGear());
    }

    @Test
    public void shiftingToDrive(){
        GearPacket gearPacket = new GearPacket(new UserInputDummy());

        gearPacket.onShifting(Gear.D);
        gearPacket.createSnapshot();

        Assert.assertEquals(Gear.D, gearPacket.getCurrentGear());
    }

    @Test
    public void shiftingToReverse(){
        GearPacket gearPacket = new GearPacket(new UserInputDummy());

        gearPacket.onShifting(Gear.R);
        gearPacket.createSnapshot();

        Assert.assertEquals(Gear.R, gearPacket.getCurrentGear());
    }

    @Test
    public void shiftingToNeutral(){
        GearPacket gearPacket = new GearPacket(new UserInputDummy());

        gearPacket.onShifting(Gear.D);
        gearPacket.onShifting(Gear.N);
        gearPacket.createSnapshot();

        Assert.assertEquals(Gear.N, gearPacket.getCurrentGear());
    }

    class UserInputFake extends UserInputDummy {
        ArrayList<IShiftingEventHandler> shiftingEventHandlers = new ArrayList<>();
        @Override
        public void subscribeShiftingEvents(IShiftingEventHandler handler) {
            this.shiftingEventHandlers.add(handler);
        }
    }
}