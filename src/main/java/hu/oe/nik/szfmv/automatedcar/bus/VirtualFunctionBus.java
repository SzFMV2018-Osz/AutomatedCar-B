package hu.oe.nik.szfmv.automatedcar.bus;

import java.util.ArrayList;
import java.util.List;

import hu.oe.nik.szfmv.automatedcar.bus.packets.ReadonlyPowertrainPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.ReadonlySteeringPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.ReadonlyVelocityPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyGearPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyIndicationPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlyPedalPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IReadonlySteeringPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.sample.ReadOnlySamplePacket;
import hu.oe.nik.szfmv.automatedcar.systemcomponents.SystemComponent;

/**
 * This is the class for the Virtual Function Bus. Components are only allowed
 * to collect sensory data exclusively using the VFB. The VFB stores the input
 * and output signals, inputs only have setters, while outputs only have getters
 * respectively.
 */
public class VirtualFunctionBus {

    public ReadOnlySamplePacket samplePacket;
    public IReadonlyIndicationPacket indicationPacket;
    public IReadonlyGearPacket gearPacket;
    public IReadonlySteeringPacket steeringWheelPacket;
    public IReadonlyPedalPacket gasPedalPacket;
    public IReadonlyPedalPacket brakePedalPacket;

    public IReadonlySensorPacket sensorPacket;
    public ReadonlyPowertrainPacket powertrainPacket;
    public ReadonlySteeringPacket steeringPacket;
    public ReadonlyVelocityPacket velocityPacket;

    private List<SystemComponent> components = new ArrayList<>();

    /**
     * Registers the provided {@link SystemComponent}
     *
     * @param comp a class that implements @{link ISystemComponent}
     */
    public void registerComponent(SystemComponent comp) {
        components.add(comp);
    }

    /**
     * Calls cyclically the registered {@link SystemComponent}s once the virtual
     * function bus has started.
     */
    public void loop() {
        for (SystemComponent comp : components) {
            comp.loop();

        }
    }
}
