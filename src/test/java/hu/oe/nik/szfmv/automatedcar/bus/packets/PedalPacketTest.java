package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyPedalPacket;
import org.junit.Assert;
import org.junit.Test;

public class PedalPacketTest {

    @Test
    public void initialize(){
        IReadonlyPedalPacket pedalPacket = new PedalPacket(new UserInputDummy());

        int expectedGasPedalPosition = 0;
        int expectedBrakePedalPosition = 0;

        Assert.assertEquals(expectedGasPedalPosition, pedalPacket.getGasPedalPosition());
        Assert.assertEquals(expectedBrakePedalPosition, pedalPacket.getBrakePedalPosition());
    }

    @Test
    public void pushGasPedal(){
        PedalPacket pedalPacket = new PedalPacket(new UserInputDummy());

        int expectedGasPedalPosition = 2;

        pedalPacket.onGasPedalPush();
        pedalPacket.createSnapshot();

        Assert.assertEquals(expectedGasPedalPosition, pedalPacket.getGasPedalPosition());
    }

    @Test
    public void releaseGasPedal(){
        PedalPacket pedalPacket = new PedalPacket(new UserInputDummy());

        int expectedGasPedalPosition = 2;

        pedalPacket.onGasPedalPush();
        pedalPacket.onGasPedalPush();
        pedalPacket.onGasPedalRelease();
        pedalPacket.createSnapshot();

        Assert.assertEquals(expectedGasPedalPosition, pedalPacket.getGasPedalPosition());
    }

    @Test
    public void pushBrakePedal(){
        PedalPacket pedalPacket = new PedalPacket(new UserInputDummy());

        int expectedBrakePedalPosition = 2;

        pedalPacket.onBrakePedalPush();
        pedalPacket.createSnapshot();

        Assert.assertEquals(expectedBrakePedalPosition, pedalPacket.getBrakePedalPosition());
    }

    @Test
    public void releaseBrakePedal(){
        PedalPacket pedalPacket = new PedalPacket(new UserInputDummy());

        int expectedBrakePedalPosition = 2;

        pedalPacket.onBrakePedalPush();
        pedalPacket.onBrakePedalPush();
        pedalPacket.onBrakePedalRelease();
        pedalPacket.createSnapshot();

        Assert.assertEquals(expectedBrakePedalPosition, pedalPacket.getBrakePedalPosition());
    }
}
