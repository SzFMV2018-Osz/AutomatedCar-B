package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.PowerTrainPacketImpl;
import hu.oe.nik.szfmv.automatedcar.engine.*;
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
        } catch (TransmissionModeChangeException e) {
            //TODO Input team handle this
        }
        // PROCESS INPUT

        if (gearBox.getTransmissionModes().getCanItMove()) {
            powertrainPacket.setSpeed(engine.calcvulationVelocity(1, virtualFunctionBus.steeringPacket.getAngularVector(), powertrainPacket.getGear(), powertrainPacket.getSpeed(),
                    powertrainPacket.getBrakePadelPosition(), powertrainPacket.getThrottlePosition()));
            engine.updateRpm(powertrainPacket.getSpeed(), gearBox.getCurrentGear());
            gearBox.updateGear(engine.getRpm());
        }
        // UPDATE OUT PACKET
        powertrainPacket.setRpm(getRpm());
        powertrainPacket.setGear(getCurrentGear());


    }
}
