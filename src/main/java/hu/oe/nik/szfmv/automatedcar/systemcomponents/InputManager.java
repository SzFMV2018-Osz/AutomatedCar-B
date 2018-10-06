package hu.oe.nik.szfmv.automatedcar.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.bus.packets.IndicationPacket;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.IUserInput;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.UserInputProvider;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.InputType;

/*
 * InputManager is responsible for the transportation of the HMI inputs
 */
public class InputManager extends SystemComponent {

    private final IUserInput userInput = UserInputProvider.getUserInput(InputType.Keyboard);

    private final IndicationPacket indicationPacket;

    public InputManager(VirtualFunctionBus virtualFunctionBus) {
        super(virtualFunctionBus);
        this.indicationPacket = new IndicationPacket(userInput);
        virtualFunctionBus.indicationPacket = this.indicationPacket;
    }

    @Override
    public void loop() {
        this.indicationPacket.createSnapshot();
    }
}
