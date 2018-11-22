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

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setTargetSpeed(float speed) {
        targetSpeed = speed;
    }

    public void setTargetCar(WorldObject car) {
        targetCar = car;
    }
}
