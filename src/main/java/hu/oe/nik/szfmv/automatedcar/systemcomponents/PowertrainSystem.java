package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.engine.CarEngine;
import hu.oe.nik.szfmv.automatedcar.engine.CarEngineType;
import hu.oe.nik.szfmv.automatedcar.engine.GearBox;
import hu.oe.nik.szfmv.automatedcar.engine.StandardCarEngineType;
import hu.oe.nik.szfmv.automatedcar.engine.TransmissionModes;


/**
 * Powertrain system is responsible for the movement of the car.
 */
public class PowertrainSystem extends SystemComponent {

    private PowertrainPacket powertrainPacket;
    private final CarEngineType engineType;
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

        powertrainPacket = new PowertrainPacket();
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
