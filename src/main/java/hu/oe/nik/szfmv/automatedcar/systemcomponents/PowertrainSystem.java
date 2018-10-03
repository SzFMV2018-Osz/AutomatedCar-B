package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.engine.CarEngine;
import hu.oe.nik.szfmv.automatedcar.engine.StandardCarEngine;

/**
 * Powertrain system is responsible for the movement of the car.
 */
public class PowertrainSystem extends SystemComponent {

    private final CarEngine engine;
    private int rpm;
    private int currentGear;

    /**
     * Creates a powertrain system that connects the Virtual Function Bus
     *
     * @param virtualFunctionBus {@link VirtualFunctionBus} used to connect
     *                           {@link SystemComponent}s
     */
    public PowertrainSystem(final VirtualFunctionBus virtualFunctionBus) {
	super(virtualFunctionBus);
	engine = new StandardCarEngine();
	rpm = 0;
	currentGear = 1;
    }

    public int getRpm() {
	return rpm;
    }

    public int getCurrentGear() {
	return currentGear;
    }

    @Override
    public void loop() {

    }

    public void updateEngine(final double wheelRotationRate) {
	if (wheelRotationRate >= 0) {
	    updateEngineState(wheelRotationRate);
	}
    }

    private void updateEngineState(final double wheelRotationRate) {
	rpm = calculateRpm(wheelRotationRate);
	updateGear();
    }

    private int calculateRpm(final double wheelRotationRate) {
	return (int) ((wheelRotationRate * engine.getGearRatios()[currentGear] * engine.getGearDifferentialRatio() * 60)
		/ (2 * Math.PI));
    }

    private void updateGear() {
	if ((currentGear < engine.getGearCount()) && (rpm > engine.getGearShiftRpm())) {
	    currentGear++;
	} else if ((currentGear > 1) && (rpm < engine.getBackGearShiftRpm())) {
	    currentGear--;
	}
    }
}
