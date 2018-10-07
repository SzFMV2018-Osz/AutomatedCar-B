package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyGearPacket;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Gear;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GearPacketTest {

    @Test
    public void initialization(){
        IReadonlyGearPacket gearPacket = new GearPacket(new UserInputDummy());

        Gear expected = gearPacket.getCurrentGear();

        Assert.assertEquals(expected, Gear.N);
    }

    @Test
    public void subscriptionToUserInput(){
        UserInputFake fake = new UserInputFake();

        IReadonlyGearPacket gearPacket = new GearPacket(fake);

        Assert.assertNotEquals(fake.shiftingEventHandlers.size(), 0);
    }

    @Test
    public void shiftingToPark(){
        GearPacket gearPacket = new GearPacket(new UserInputDummy());

        gearPacket.onShifting(Gear.P);

        Assert.assertEquals(gearPacket.getCurrentGear(), Gear.P);
    }

    @Test
    public void shiftingToDrive(){
        GearPacket gearPacket = new GearPacket(new UserInputDummy());

        gearPacket.onShifting(Gear.D);

        Assert.assertEquals(gearPacket.getCurrentGear(), Gear.D);
    }

    @Test
    public void shiftingToReverse(){
        GearPacket gearPacket = new GearPacket(new UserInputDummy());

        gearPacket.onShifting(Gear.R);

        Assert.assertEquals(gearPacket.getCurrentGear(), Gear.R);
    }

    @Test
    public void shiftingToNeutral(){
        GearPacket gearPacket = new GearPacket(new UserInputDummy());

        gearPacket.onShifting(Gear.D);
        gearPacket.onShifting(Gear.N);

        Assert.assertEquals(gearPacket.getCurrentGear(), Gear.N);
    }

    class UserInputFake extends UserInputDummy {

        ArrayList<IShiftingEventHandler> shiftingEventHandlers = new ArrayList<>();

        @Override
        public void subscribeShiftingEvents(IShiftingEventHandler handler) {
            this.shiftingEventHandlers.add(handler);
        }
    }
}
