package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyPedalPacket;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.InputManager;
import org.junit.Assert;
import org.junit.Test;

public class PedalPacketTest {

    @Test
    public void initialize(){
        IReadonlyPedalPacket pedalPacket = new PedalPacket(
                new GraduallyChangeableDummy(),
                new UserInputDummy(),
                PedalType.Gas,
                InputManager.GAS_PEDAL_TIME_IN_MILLISECS);

        int expectedPedalPosition = 0;

        Assert.assertEquals(expectedPedalPosition, pedalPacket.getPedalPosition());
    }

    @Test
    public void onPushPedal(){
        GraduallyChangeableMock mock = new GraduallyChangeableMock();

        PedalPacket pedalPacket = new PedalPacket(
                mock,
                new UserInputDummy(),
                PedalType.Gas,
                InputManager.GAS_PEDAL_TIME_IN_MILLISECS);

        pedalPacket.onPedalPush();
        mock.currentValue = PedalPacket.PEDAL_MAX_POSITION;
        pedalPacket.createSnapshot();

        Assert.assertEquals(PedalPacket.PEDAL_MIN_POSITION, mock.from);
        Assert.assertEquals(PedalPacket.PEDAL_MAX_POSITION, mock.to);
        Assert.assertEquals(InputManager.GAS_PEDAL_TIME_IN_MILLISECS, mock.milliseconds);
        Assert.assertEquals(PedalPacket.PEDAL_MAX_POSITION, pedalPacket.getPedalPosition());

    }

    @Test
    public void releaseGasPedal(){
        GraduallyChangeableMock mock = new GraduallyChangeableMock();

        PedalPacket pedalPacket = new PedalPacket(
                mock,
                new UserInputDummy(),
                PedalType.Gas,
                InputManager.GAS_PEDAL_TIME_IN_MILLISECS);

        mock.currentValue = PedalPacket.PEDAL_MAX_POSITION / 2;
        pedalPacket.createSnapshot();

        pedalPacket.onPedalRelease();

        Assert.assertEquals(PedalPacket.PEDAL_MAX_POSITION / 2, mock.from);
        Assert.assertEquals(PedalPacket.PEDAL_MIN_POSITION, mock.to);
        Assert.assertEquals(InputManager.GAS_PEDAL_TIME_IN_MILLISECS / 2, mock.milliseconds);
    }
}
