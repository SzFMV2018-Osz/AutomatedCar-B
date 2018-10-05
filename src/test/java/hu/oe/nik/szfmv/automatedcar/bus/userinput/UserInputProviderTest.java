package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.InputType;
import org.junit.Test;
import org.junit.Assert;

public class UserInputProviderTest {
    @Test
    public void getKeyboardUserInputProvider(){
        IUserInput input = UserInputProvider.getUserInput(InputType.Keyboard);
        Assert.assertTrue(input instanceof KeyboardUserInput);
    }

    @Test
    public void getKeyboardUserInputProviderExpectSameResult(){
        IUserInput input1 = UserInputProvider.getUserInput(InputType.Keyboard);
        IUserInput input2 = UserInputProvider.getUserInput(InputType.Keyboard);
        Assert.assertEquals(input1,input2);
    }
}
