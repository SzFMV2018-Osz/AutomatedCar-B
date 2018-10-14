package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.PowerTrainPacketImpl;
import hu.oe.nik.szfmv.automatedcar.engine.CarEngine;
import hu.oe.nik.szfmv.automatedcar.engine.CarEngineType;
import hu.oe.nik.szfmv.automatedcar.engine.GearBox;
import hu.oe.nik.szfmv.automatedcar.engine.StandardCarEngineType;
import hu.oe.nik.szfmv.automatedcar.engine.TransmissionModes;
import hu.oe.nik.szfmv.automatedcar.engine.exception.TransmissionModeChangeException;


/**
 * Powertrain system is responsible for the movement of the car.
 */
public class PowertrainSystem extends SystemComponent {

    private final CarEngineType engineType;
    private PowerTrainPacketImpl powertrainPacket;
    private CarEngine engine;
    private GearBox gearBox;


    /**
     * Creates a powertrain system that connects the Virtual Function Bus
     *
     * @param virtualFunctionBus {@link VirtualFunctionBus} used to connect
     *                           {@link SystemComponent}s
     */
    public PowertrainSystem(VirtualFunctionBus virtualFunctionBus) {
        super(virtualFunctionBus);

        powertrainPacket = new PowerTrainPacketImpl();
        virtualFunctionBus.powertrainPacket = powertrainPacket;

        engineType = new StandardCarEngineType();
        engine = new CarEngine(engineType);
        gearBox = new GearBox(engineType);
    }

    public int getRpm() {
        return engine.getRpm();
    }

    public int getCurrentGear() {
        return gearBox.getCurrentGear();
    }

    public TransmissionModes getCurrentAutomaticTransmissionModes() {
        return gearBox.getTransmissionModes();
    }

    @Override
    public void loop() {
        // GET INPUT
        try {
            gearBox.changeTransmissionMode(powertrainPacket.getTransmissionMode(), powertrainPacket.getRpm());
        } catch (TransmissionModeChangeException e){
            //TODO Input team handle this
        }

        // PROCESS INPUT

        // UPDATE OUT PACKET
        powertrainPacket.setRpm(getRpm());
        powertrainPacket.setGear(getCurrentGear());
    }

    /**
     * @param wheelRotationRate the current wheel rotation rate
     */
    public void updateEngine(final double wheelRotationRate) {
        if (wheelRotationRate >= 0 && gearBox.getTransmissionModes().getCanItMove()) {
            engine.updateRpm(wheelRotationRate, gearBox.getCurrentGear());
            gearBox.updateGear(engine.getRpm());
        }

    }
}
