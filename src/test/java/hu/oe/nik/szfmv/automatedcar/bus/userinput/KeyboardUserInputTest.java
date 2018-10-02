package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class KeyboardUserInputTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testPedalHandlerNull(){
        KeyboardUserInput kui=new KeyboardUserInput();

        exception.expect(IllegalArgumentException.class);
        kui.subscribePedalEvents(null, PedalType.Brake);

        Assert.assertEquals(0, kui.getGasPedalEventHandlers().size());
    }

    @Test
    public void testPedalTypeNull(){
        KeyboardUserInput kui=new KeyboardUserInput();

        exception.expect(IllegalArgumentException.class);
        kui.subscribePedalEvents(new PedalHandlerDummy(), null);

        Assert.assertEquals(0, kui.getGasPedalEventHandlers().size());
    }

    @Test
    public void testPedalHandlerSubscription(){
        KeyboardUserInput kui=new KeyboardUserInput();

        kui.subscribePedalEvents(new PedalHandlerDummy(),PedalType.Gas);

        Assert.assertEquals(1, kui.getGasPedalEventHandlers().size());
    }

    @Test
    public  void testPedalHandlerUnsubscription(){
        KeyboardUserInput kui=new KeyboardUserInput();
        IPedalEventHandler dummy = new PedalHandlerDummy();
        kui.subscribePedalEvents(dummy, PedalType.Gas);

        kui.unsubscribePedalEvents(dummy, PedalType.Gas);

        Assert.assertEquals(0, kui.getGasPedalEventHandlers().size());
    }

    @Test
    public void testShiftingHandlerNull(){
        KeyboardUserInput kui=new KeyboardUserInput();

        exception.expect(IllegalArgumentException.class);
        kui.subscribeShiftingEvents(null);

        Assert.assertEquals(0, kui.getShiftingEventHandlers().size());
    }

    @Test
    public void testIndicationHandlerNull(){
        KeyboardUserInput kui=new KeyboardUserInput();

        exception.expect(IllegalArgumentException.class);
        kui.subscribeIndicationEvents(null);

        Assert.assertEquals(0, kui.getIndicationEventHandlers().size());
    }

    @Test
    public void testSteeringHandlerNull(){
        KeyboardUserInput kui=new KeyboardUserInput();

        exception.expect(IllegalArgumentException.class);
        kui.subscribeSteeringEvents(null);

        Assert.assertEquals(0, kui.getSteeringEventHandlers().size());
    }

    class PedalHandlerDummy implements IPedalEventHandler
    {
        @Override
        public void onPedalPush() {}
        @Override
        public void onPedalRelease() {}
    }
}