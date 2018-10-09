package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.common.enums.Gear;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;
import hu.oe.nik.szfmv.common.ConfigProvider;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Date;

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

    @Test
    public void testShiftingHandling(){
        ShiftingHandlerFake fake=new ShiftingHandlerFake();
        KeyboardUserInput kui=new KeyboardUserInput();
        kui.subscribeShiftingEvents(fake);
        Component dummy=new Component() {
            @Override
            public String getName() {
                return super.getName();
            }
        };
        int keyCode=ConfigProvider.provide().getLong("keyboard.gear_p").intValue();
        KeyEvent e= new KeyEvent(dummy,0,new Date().getTime(),0,keyCode,KeyEvent.CHAR_UNDEFINED);

        for(int i=0;i<3;i++)
            kui.keyPressed(e);
        kui.keyReleased(e);

        Assert.assertEquals(1, fake.getCounter());
        Assert.assertSame(fake.getGear(), Gear.P);
    }

    class PedalHandlerDummy implements IPedalEventHandler
    {
        @Override
        public void onGasPedalPush() {}
        @Override
        public void onGasPedalRelease() {}
        @Override
        public void onBrakePedalPush() {}
        @Override
        public void onBrakePedalRelease() {}
    }

    class ShiftingHandlerFake implements IShiftingEventHandler
    {
        private int counter=0;
        private Gear gear=null;

        public int getCounter(){
            return counter;
        }
        public Gear getGear(){
            return gear;
        }

        @Override
        public void onShifting(Gear gear) {
            this.counter++;
            this.gear=gear;
        }
    }
}