package hu.oe.nik.szfmv.automatedcar.engine;

public class GearBox {

    private CarEngineType engineType;
    private int currentGear;

    public GearBox(final CarEngineType engineType) {
	this.engineType = engineType;
	currentGear = 1;
    }

    public int getCurrentGear() {
	return currentGear;
    }

    public void updateGear(final int rpm) {
	if ((currentGear < engineType.getGearCount()) && (rpm > engineType.getGearShiftRpm())) {
	    currentGear++;
	} else if ((currentGear > 1) && (rpm < engineType.getBackGearShiftRpm())) {
	    currentGear--;
	}
    }
}
