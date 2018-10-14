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

    @Override
    public void loop() {
        // GET INPUT
        transmissionChange();
        // PROCESS INPUT
       updateEngine();
        // UPDATE OUT PACKET
        updateBusProperties();

        System.out.println("speed:" + powertrainPacket.getSpeed());
        System.out.println("rpm:" + powertrainPacket.getRpm());
        System.out.println("Vector X:" + virtualFunctionBus.steeringPacket.getAngularVector()[0] + " Vector Y:" + virtualFunctionBus.steeringPacket.getAngularVector()[1]);
    }

    private void updateEngine()
    {
        if (virtualFunctionBus.powertrainPacket.getTransmissionMode().getCanItMove()) {
            //TODO FRAME KI MOCKOLVA
            powertrainPacket.setSpeed(engine.calcvulationVelocity(0.42, virtualFunctionBus.steeringPacket.getAngularVector(), powertrainPacket.getGear(), powertrainPacket.getSpeed(),
                    powertrainPacket.getBrakePadelPosition(), powertrainPacket.getThrottlePosition()));
            engine.updateRpm((int) Math.round(powertrainPacket.getSpeed()), gearBox.getCurrentGear());
            gearBox.updateGear(engine.getRpm());
        }
    }

    private void transmissionChange()
    {
        try {
            gearBox.changeTransmissionMode(powertrainPacket.getTransmissionMode(), powertrainPacket.getRpm());
        } catch (TransmissionModeChangeException e) {
            //TODO Input team handle this
        }
    }

    private void updateBusProperties()
    {
        powertrainPacket.setRpm(getRpm());
        powertrainPacket.setGear(getCurrentGear());
    }


}
