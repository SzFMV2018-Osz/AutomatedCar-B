package hu.oe.nik.szfmv.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.InputManager;
import org.junit.Assert;
import org.junit.Test;

public class InputManagerTest {
    @Test
    public void Init() {
        VirtualFunctionBus vfb = new VirtualFunctionBus();
        InputManager im = new InputManager(vfb);

        Assert.assertNotNull(im);
    }

    @Test
    public void PacketInitializations() {
        VirtualFunctionBus vfb = new VirtualFunctionBus();
        InputManager im = new InputManager(vfb);

        Assert.assertNotNull(vfb.gearPacket);
        Assert.assertNotNull(vfb.indicationPacket);
        Assert.assertNotNull(vfb.indicationPacket);
    }
}
