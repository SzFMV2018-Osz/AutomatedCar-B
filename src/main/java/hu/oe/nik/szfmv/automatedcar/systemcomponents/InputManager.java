package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.*;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.IUserInput;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.UserInputProvider;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.InputType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;

/**
 * InputManager is responsible for the transportation of the HMI inputs
 */
public class InputManager extends SystemComponent {

    public static final int GAS_PEDAL_TIME_IN_MILLISECS = 1000;
    public static final int BRAKE_PEDAL_TIME_IN_MILLISECS = 500;

    private final IUserInput userInput = UserInputProvider.getUserInput(InputType.Keyboard);
    private final IndicationPacket indicationPacket;
    private final GearPacket gearPacket;
    private final SteeringWheelPacket steeringWheelPacket;
    private final PedalPacket gasPedalPacket;
    private final PedalPacket brakePedalPacket;

    /**
     * Constructor of InputManager class.
     *
     * @param virtualFunctionBus - virtual function bus
     */
    public InputManager(VirtualFunctionBus virtualFunctionBus) {
        super(virtualFunctionBus);
        this.indicationPacket = new IndicationPacket(userInput);
        this.gearPacket = new GearPacket(userInput);
        this.steeringWheelPacket = new SteeringWheelPacket(new GraduallyChangeable(),
                UserInputProvider.getUserInput(InputType.Keyboard));
        this.gasPedalPacket = new PedalPacket(new GraduallyChangeable(), userInput, PedalType.Gas,
                GAS_PEDAL_TIME_IN_MILLISECS);
        this.brakePedalPacket = new PedalPacket(new GraduallyChangeable(), userInput, PedalType.Brake,
                BRAKE_PEDAL_TIME_IN_MILLISECS);

        virtualFunctionBus.indicationPacket = this.indicationPacket;
        virtualFunctionBus.gearPacket = this.gearPacket;
        virtualFunctionBus.steeringWheelPacket = this.steeringWheelPacket;
        virtualFunctionBus.gasPedalPacket = this.gasPedalPacket;
        virtualFunctionBus.brakePedalPacket = this.brakePedalPacket;
    }

    @Override
    public void loop() {
        this.indicationPacket.createSnapshot();
        this.gearPacket.createSnapshot();
        this.steeringWheelPacket.createSnapshot();
        this.gasPedalPacket.createSnapshot();
        this.brakePedalPacket.createSnapshot();
    }
}