package hu.oe.nik.szfmv.automatedcar.bus.userinput;

import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.Direction;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.enums.PedalType;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IIndicationEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IPedalEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.IShiftingEventHandler;
import hu.oe.nik.szfmv.automatedcar.bus.userinput.eventhandlers.ISteeringEventHandler;
import hu.oe.nik.szfmv.common.ConfigProvider;
import hu.oe.nik.szfmv.common.enums.Gear;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.HashMap;

public class KeyboardUserInputTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testPedalHandlerNull() {
        KeyboardUserInput kui = new KeyboardUserInput();

        exception.expect(IllegalArgumentException.class);
        kui.subscribePedalEvents(null, PedalType.Brake);

        Assert.assertEquals(0, kui.getGasPedalEventHandlers().size());
    }

    @Test
    public void testPedalTypeNull() {
        KeyboardUserInput kui = new KeyboardUserInput();

        exception.expect(IllegalArgumentException.class);
        kui.subscribePedalEvents(new PedalHandlerDummy(), null);

        Assert.assertEquals(0, kui.getGasPedalEventHandlers().size());
    }

    @Test
    public void testPedalHandlerSubscription() {
        KeyboardUserInput kui = new KeyboardUserInput();

        kui.subscribePedalEvents(new PedalHandlerDummy(), PedalType.Gas);

        Assert.assertEquals(1, kui.getGasPedalEventHandlers().size());
    }

    @Test
    public void testPedalHandlerUnsubscription() {
        KeyboardUserInput kui = new KeyboardUserInput();
        IPedalEventHandler dummy = new PedalHandlerDummy();
        kui.subscribePedalEvents(dummy, PedalType.Gas);

        kui.unsubscribePedalEvents(dummy, PedalType.Gas);

        Assert.assertEquals(0, kui.getGasPedalEventHandlers().size());
    }

    @Test
    public void testShiftingHandlerNull() {
        KeyboardUserInput kui = new KeyboardUserInput();

        exception.expect(IllegalArgumentException.class);
        kui.subscribeShiftingEvents(null);

        Assert.assertEquals(0, kui.getShiftingEventHandlers().size());
    }

    @Test
    public void testShiftingHandlerSubscription() {
        KeyboardUserInput kui = new KeyboardUserInput();

        kui.subscribeShiftingEvents(new ShiftingHandlerDummy());

        Assert.assertEquals(1, kui.getShiftingEventHandlers().size());
    }

    @Test
    public void testIndicationHandlerNull() {
        KeyboardUserInput kui = new KeyboardUserInput();

        exception.expect(IllegalArgumentException.class);
        kui.subscribeIndicationEvents(null);

        Assert.assertEquals(0, kui.getIndicationEventHandlers().size());
    }

    @Test
    public void testIndicationHandlerSubscription() {
        KeyboardUserInput kui = new KeyboardUserInput();

        kui.subscribeIndicationEvents(new IndicatorHandlerDummy());

        Assert.assertEquals(1, kui.getIndicationEventHandlers().size());
    }

    @Test
    public void testSteeringHandlerNull() {
        KeyboardUserInput kui = new KeyboardUserInput();

        exception.expect(IllegalArgumentException.class);
        kui.subscribeSteeringEvents(null);

        Assert.assertEquals(0, kui.getSteeringEventHandlers().size());
    }

    @Test
    public void testSteeringHandlerSubscription() {
        KeyboardUserInput kui = new KeyboardUserInput();

        kui.subscribeSteeringEvents(new SteeringHandlerDummy());

        Assert.assertEquals(1, kui.getSteeringEventHandlers().size());
    }

    @Test
    public void testShiftingHandling() {
        ShiftingHandlerFake fake = new ShiftingHandlerFake();
        KeyboardUserInput kui = new KeyboardUserInput();
        kui.subscribeShiftingEvents(fake);
        Component dummy = new Component() {
            @Override
            public String getName() {
                return super.getName();
            }
        };
        int keyCode = ConfigProvider.provide().getLong("keyboard.gear_p").intValue();
        KeyEvent e = new KeyEvent(dummy, 0, new Date().getTime(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);

        for (int i = 0; i < 3; i++) {
            kui.keyPressed(e);
        }
        kui.keyReleased(e);

        Assert.assertEquals(1, fake.getCounter());
        Assert.assertSame(fake.getGear(), Gear.P);
    }

    @Test
    public void testKeyPressed_Gas() {
        KeyboardUserInput kui = new KeyboardUserInput();
        HandlerFake hf = new HandlerFake();
        kui.subscribePedalEvents(hf, PedalType.Gas);
        Component dummy = new Component() {
            @Override
            public String getName() {
                return super.getName();
            }
        };
        int keyCode = ConfigProvider.provide().getLong("keyboard.gas").intValue();
        KeyEvent e = new KeyEvent(dummy, 0, new Date().getTime(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);

        kui.keyPressed(e);

        boolean result = hf.fired.containsKey("pedalPush");
        Assert.assertTrue(result);
    }

    @Test
    public void testKeyReleased_Gas() {
        KeyboardUserInput kui = new KeyboardUserInput();
        HandlerFake hf = new HandlerFake();
        kui.subscribePedalEvents(hf, PedalType.Gas);
        Component dummy = new Component() {
            @Override
            public String getName() {
                return super.getName();
            }
        };
        int keyCode = ConfigProvider.provide().getLong("keyboard.gas").intValue();
        KeyEvent e = new KeyEvent(dummy, 0, new Date().getTime(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);

        kui.keyPressed(e);
        kui.keyReleased(e);

        boolean result = hf.fired.containsKey("pedalRelease");
        Assert.assertTrue(result);
    }

    @Test
    public void testKeyPressed_SteeringLeft() {
        KeyboardUserInput kui = new KeyboardUserInput();
        HandlerFake hf = new HandlerFake();
        kui.subscribeSteeringEvents(hf);
        Component dummy = new Component() {
            @Override
            public String getName() {
                return super.getName();
            }
        };
        int keyCode = ConfigProvider.provide().getLong("keyboard.steer_left").intValue();
        KeyEvent e = new KeyEvent(dummy, 0, new Date().getTime(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);

        kui.keyPressed(e);

        boolean result = hf.fired.containsKey("steer-Left");
        Assert.assertTrue(result);
    }

    @Test
    public void testKeyReleased_Steering() {
        KeyboardUserInput kui = new KeyboardUserInput();
        HandlerFake hf = new HandlerFake();
        kui.subscribeSteeringEvents(hf);
        Component dummy = new Component() {
            @Override
            public String getName() {
                return super.getName();
            }
        };
        int keyCode = ConfigProvider.provide().getLong("keyboard.steer_left").intValue();
        KeyEvent e = new KeyEvent(dummy, 0, new Date().getTime(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);

        kui.keyPressed(e);
        kui.keyReleased(e);

        boolean result = hf.fired.containsKey("steerReleased");
        Assert.assertTrue(result);
    }

    class PedalHandlerDummy implements IPedalEventHandler {
        @Override
        public void onPedalPush() {
        }

        @Override
        public void onPedalRelease() {
        }
    }

    class ShiftingHandlerDummy implements IShiftingEventHandler {


        @Override
        public void onShifting(Gear gear) {

        }
    }

    class IndicatorHandlerDummy implements IIndicationEventHandler {

        @Override
        public void onIndication(Direction direction) {

        }
    }

    class SteeringHandlerDummy implements ISteeringEventHandler {

        @Override
        public void onSteering(Direction direction) {

        }

        @Override
        public void onSteeringReleased() {

        }
    }

    class ShiftingHandlerFake implements IShiftingEventHandler {
        private int counter = 0;
        private Gear gear = null;

        public int getCounter() {
            return counter;
        }

        public Gear getGear() {
            return gear;
        }

        @Override
        public void onShifting(Gear gear) {
            this.counter++;
            this.gear = gear;
        }
    }

    class HandlerFake implements IPedalEventHandler, ISteeringEventHandler,
            IIndicationEventHandler, IShiftingEventHandler {

        HashMap<String, Boolean> fired = new HashMap<>();

        @Override
        public void onIndication(Direction direction) {
            fired.put("indication", true);
        }

        @Override
        public void onPedalPush() {
            fired.put("pedalPush", true);
        }

        @Override
        public void onPedalRelease() {
            fired.put("pedalRelease", true);
        }

        @Override
        public void onShifting(Gear gear) {
            fired.put("shift-" + gear.name(), true);
        }

        @Override
        public void onSteering(Direction direction) {
            fired.put("steer-" + direction.name(), true);
        }

        @Override
        public void onSteeringReleased() {
            fired.put("steerReleased", true);
        }
    }
}