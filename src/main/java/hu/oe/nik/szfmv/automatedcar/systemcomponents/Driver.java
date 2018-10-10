package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.sample.SamplePacket;

public class Driver extends SystemComponent {

    private final SamplePacket samplePacket;
    private final int constantGasPedalPosition = 5;

    /**
     * @param virtualFunctionBus the virtual function bus
     */
    public Driver(VirtualFunctionBus virtualFunctionBus) {
        super(virtualFunctionBus);
        samplePacket = new SamplePacket();
        virtualFunctionBus.samplePacket = samplePacket;
    }

    @Override
    public void loop() {
        samplePacket.setGaspedalPosition(constantGasPedalPosition);
    }
}