package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.PowerTrainPacketImpl;
import hu.oe.nik.szfmv.automatedcar.engine.CarEngine;
import hu.oe.nik.szfmv.automatedcar.engine.CarEngineType;
import hu.oe.nik.szfmv.automatedcar.engine.GearBox;
import hu.oe.nik.szfmv.automatedcar.engine.StandardCarEngineType;
import hu.oe.nik.szfmv.automatedcar.engine.TractionForce;
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

    public CarEngineType getEngineType() {
        return engineType;
    }

    @Override
    public void loop() {
        // GET INPUT
        transmissionChange();
        // PROCESS INPUT
        updateEngine();
        // UPDATE OUT PACKET
        updateBusProperties();

        System.out.println("speed:" + virtualFunctionBus.velocityPacket.getSpeed());
        System.out.println("rpm:" + powertrainPacket.getRpm());
        System.out.println("Vector X:" + virtualFunctionBus.steeringPacket.getAngularVector()[0] + " Vector Y:"
                + virtualFunctionBus.steeringPacket.getAngularVector()[1]);
    }

    private void updateEngine() {
        engine.updateRpm((int) virtualFunctionBus.velocityPacket.getSpeed(), gearBox.getCurrentGear());
        gearBox.updateGear(engine.getRpm());
    }

    private void transmissionChange() {
        try {
            gearBox.changeTransmissionMode(powertrainPacket.getTransmissionMode(), powertrainPacket.getRpm());
        } catch (TransmissionModeChangeException e) {
            // TODO Input team handle this
        } finally {
            if (gearBox.getTransmissionModes() != powertrainPacket.getTransmissionMode()) {
                powertrainPacket.setTransmissionMode(gearBox.getTransmissionModes());
            }
        }
    }

    private void updateBusProperties() {
        powertrainPacket.setRpm(getRpm());
        powertrainPacket.setGear(getCurrentGear());
    }

    public double[] calculateTractionForce() {
        if (powertrainPacket.getTransmissionMode().equals(TransmissionModes.Drive)) {
            return calculateTractionForceFomGear(gearBox.getCurrentGear());
        } else if (powertrainPacket.getTransmissionMode().equals(TransmissionModes.Reverse)) {
            double[] tractionForce = calculateTractionForceFomGear(0);
            return new double[] { tractionForce[0] * -1, tractionForce[1] * -1 };
        } else {
            return new double[] { 0, 0 };
        }
    }

    private double[] calculateTractionForceFomGear(int gear) {
        return TractionForce.calculateTractionForce(virtualFunctionBus.steeringPacket.getAngularVector(),
                engine.calculateDriveTorque(powertrainPacket.getThrottlePosition(), gear), engineType.getWheelRadius());
    }
}
