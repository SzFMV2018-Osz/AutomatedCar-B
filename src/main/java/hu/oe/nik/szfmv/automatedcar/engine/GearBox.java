package hu.oe.nik.szfmv.automatedcar.engine;

import hu.oe.nik.szfmv.automatedcar.engine.exception.TransmissionModeChangeException;

public class GearBox {

    private CarEngineType engineType;
    private int currentGear;
    private TransmissionModes transmissionModes;

    public GearBox(final CarEngineType engineType) {
        this.engineType = engineType;
        currentGear = 1;
    }

    public TransmissionModes getTransmissionModes() {
        return transmissionModes;
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

    /**
     * Change transmission mode if it is possible
     * @param nextTransmissionMode nex transnmission mode
     * @param rpm actual rmp
     * @throws TransmissionModeChangeException if you want to change mode but it is impossible
     */
    public void changeTransmissionMode(TransmissionModes nextTransmissionMode, final int rpm)
            throws TransmissionModeChangeException {
        if (nextTransmissionMode == TransmissionModes.Neutral) {
            this.transmissionModes = nextTransmissionMode;
        } else if (TransmissionModes.Drive == transmissionModes) {
            reserveTransmissionModeHandler(nextTransmissionMode, rpm);
        } else if (TransmissionModes.Reverse == transmissionModes) {
            driveTransmissionModeHandler(nextTransmissionMode, rpm);
        } else {
            this.transmissionModes = nextTransmissionMode;
        }
    }

    private void reserveTransmissionModeHandler(TransmissionModes nextTransmissionModes, final int rpm)
            throws TransmissionModeChangeException {
        if (getCurrentGear() != 1 || rpm != 0) {
            throw new TransmissionModeChangeException(transmissionModes, nextTransmissionModes);
        } else {
            this.transmissionModes = nextTransmissionModes;
            this.currentGear = 0;
        }
    }

    private void driveTransmissionModeHandler(TransmissionModes nextTransmissionModes, final int rpm)
            throws TransmissionModeChangeException {
        if ((rpm != 0)) {
            throw new TransmissionModeChangeException(transmissionModes, nextTransmissionModes);
        } else {
            this.transmissionModes = nextTransmissionModes;
            this.currentGear = 1;
        }
    }

}
