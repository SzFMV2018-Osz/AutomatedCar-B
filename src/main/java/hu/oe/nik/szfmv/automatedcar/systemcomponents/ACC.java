package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.environment.WorldObject;

public class ACC {
    private WorldObject targetCar;
    private boolean isActive;
    private float targetSpeed;

    /**
     * Default Constructor
     */
    public ACC() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * @param active sets the active state of the ACC
     */
    public void setActive(boolean active) {
        isActive = active;

        if (!isActive) {
            targetCar = null;
        }
    }

    /**
     * @param speed sets the ACC speed to maintain
     */
    public void setTargetSpeed(float speed) {
        targetSpeed = speed;
    }

    /**
     * @param car sets the car in front of ours
     */
    public void setTargetCar(WorldObject car) {
        targetCar = car;
    }

    /**
     * Disables the ACC if any brake state is true
     *
     * @param brake          the manual brake state
     * @param emergencyBrake the automatic emergency brake
     */
    public void handleBrakingState(boolean brake, boolean emergencyBrake) {
        if (brake || emergencyBrake) {
            isActive = false;
        }
    }
}
