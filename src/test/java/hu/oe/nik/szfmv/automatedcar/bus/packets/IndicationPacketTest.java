package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyIndicationPacket;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Direction;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IIndicationEventHandler;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class IndicationPacketTest {

    @Test
    public void Initialization(){
        IReadonlyIndicationPacket indicationPacket=new IndicationPacket(new UserInputDummy());

        int expected = indicationPacket.getIndicatorDirection();

        Assert.assertEquals(expected,0);
    }

    @Test
    public void SubscriptionToUserInput(){
        UserInputFake fake=new UserInputFake();
        IReadonlyIndicationPacket indicationPacket=new IndicationPacket(fake);

        Assert.assertNotEquals(fake.indicationHandlers.size(),0);
    }

    @Test
    public void Snapshot(){
        IndicationPacket indicationPacket=new IndicationPacket(new UserInputDummy());

        indicationPacket.createSnapshot();
        indicationPacket.onIndication(Direction.Left);

        Assert.assertEquals(indicationPacket.getIndicatorDirection(), 0);
    }

    @Test
    public void IndicationToLeftFromNeutral(){
        IndicationPacket indicationPacket=new IndicationPacket(new UserInputDummy());

        indicationPacket.onIndication(Direction.Left);
        indicationPacket.createSnapshot();

        Assert.assertEquals(indicationPacket.getIndicatorDirection(), -1);
    }

    @Test
    public void IndicationToLeftThenRight(){
        IndicationPacket indicationPacket=new IndicationPacket(new UserInputDummy());

        indicationPacket.onIndication(Direction.Left);
        indicationPacket.onIndication(Direction.Right);
        indicationPacket.createSnapshot();

        Assert.assertEquals(indicationPacket.getIndicatorDirection(), 1);
    }

    @Test
    public void IndicationSwitchingOff(){
        IndicationPacket indicationPacket=new IndicationPacket(new UserInputDummy());

        indicationPacket.onIndication(Direction.Right);
        indicationPacket.onIndication(Direction.Right);

        Assert.assertEquals(indicationPacket.getIndicatorDirection(), 0);
    }

    class UserInputFake extends UserInputDummy {

        ArrayList<IIndicationEventHandler> indicationHandlers=new ArrayList<>();

        @Override
        public void subscribeIndicationEvents(IIndicationEventHandler handler) {
            this.indicationHandlers.add(handler);
        }
    }
}
