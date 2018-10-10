package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.InputType;

public class UserInputProvider {
    private static IUserInput keyboardUserInput;

    private UserInputProvider() {
    }

    /**
     * Provide the interface of the chosen input source which broadcast events user activities
     * (e.g. pedal push/shifting etc.)
     *
     * @param type - Input source identifier
     * @return a {@Link IUserInput} interface where you can subscribe to the events
     */
    public static IUserInput getUserInput(InputType type) {
        if (type == InputType.Keyboard) {
            if (keyboardUserInput == null) {
                keyboardUserInput = new KeyboardUserInput();
            }
            return keyboardUserInput;
        }
        throw new IllegalArgumentException("Not implemented input type!");
    }
}
