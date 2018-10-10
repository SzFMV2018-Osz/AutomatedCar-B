package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.GearPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.GraduallyChangeable;
import hu.oe.nik.szfmv.automatedcar.bus.packets.IndicationPacket;
import hu.oe.nik.szfmv.automatedcar.bus.packets.SteeringWheelPacket;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.IUserInput;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.UserInputProvider;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.InputType;

/*
 * InputManager is responsible for the transportation of the HMI inputs
 */
public class InputManager extends SystemComponent {

    private final IUserInput userInput = UserInputProvider.getUserInput(InputType.Keyboard);
    private final IndicationPacket indicationPacket;
    private final GearPacket gearPacket;
    private final SteeringWheelPacket steeringWheelPacket;

    public InputManager(VirtualFunctionBus virtualFunctionBus) {
        super(virtualFunctionBus);
        this.indicationPacket = new IndicationPacket(userInput);
        this.gearPacket = new GearPacket(userInput);
        this.steeringWheelPacket = new SteeringWheelPacket(new GraduallyChangeable(),
                UserInputProvider.getUserInput(InputType.Keyboard));

        virtualFunctionBus.indicationPacket = this.indicationPacket;
        virtualFunctionBus.gearPacket = this.gearPacket;
        virtualFunctionBus.steeringWheelPacket = this.steeringWheelPacket;
    }

    @Override
    public void loop() {
        this.indicationPacket.createSnapshot();
        this.gearPacket.createSnapshot();
        this.steeringWheelPacket.createSnapshot();
    }
}
