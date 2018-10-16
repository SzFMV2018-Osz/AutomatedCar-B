package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.InputType;

public class UserInputProvider {
    private static IUserInput keyboardUserInput;

    private UserInputProvider() {
    }

    /**
     * Gets the UserInput.
     * @param type - Type
     * @return UserInput
     */
    public static IUserInput getUserInput(InputType type) {

        if (type == InputType.Keyboard) {
            if (keyboardUserInput == null) {
                keyboardUserInput = new KeyboardUserInput();
            }
            return  keyboardUserInput;
        }

        throw new IllegalArgumentException("Not implemented input type!");
    }
}
