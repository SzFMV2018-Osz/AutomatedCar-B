package hu.oe.nik.szfmv.automatedcar.sensor;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.ISensorDebugEventHandler;

/**
 * This class can visually mark objects relevant to the Camera and Radar
 */
public class SensorDebug implements ISensorDebugEventHandler {
    private boolean isActive;

    /**
     *  Toggles the activation of the camera/radar debug mode
     */
    public void toggleActive() {
        isActive = !isActive;

        System.out.println("Debug mode is: " + isActive);
    }

    @Override
    public void onSensorDebugToggle() {
        toggleActive();
    }
}
