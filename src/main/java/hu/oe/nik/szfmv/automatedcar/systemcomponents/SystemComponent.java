package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;

/**
 * This class represents common features for system components By extending this
 * class, the component is registered towards the virtual function bus
 * automatically on instantiation.
 */
public abstract class SystemComponent {
    protected final VirtualFunctionBus virtualFunctionBus;

    /**
     * Constructor of SystemComponent class.
     * @param virtualFunctionBus - the virtual function bus
     */
    protected SystemComponent(VirtualFunctionBus virtualFunctionBus) {
        this.virtualFunctionBus = virtualFunctionBus;
        virtualFunctionBus.registerComponent(this);
    }

    /**
     * Ensures the continuous behaviour of the system component.
     */
    public abstract void loop();
}
