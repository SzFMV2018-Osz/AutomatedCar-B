package hu.oe.nik.szfmv.automatedcar.bus.packets;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Enclosed.class)
public class GraduallyChangeableTest {

    @RunWith(Parameterized.class)
    public static class ParameterizedPart {

        @Parameterized.Parameter
        public int pClockStart;
        @Parameterized.Parameter(1)
        public int pFrom;
        @Parameterized.Parameter(2)
        public int pTo;
        @Parameterized.Parameter(3)
        public int pMilliseconds;
        @Parameterized.Parameter(4)
        public int pCallTime;
        @Parameterized.Parameter(5)
        public int pExpected;

        @Parameterized.Parameters
        public static Collection<Object[]> data() {

            List<Object[]> increasing = Arrays.asList(new Object[][]{
                    {0, 0, 100, 1000, 500, 50}, {0, 0, 100, 1000, 759, 75}, {0, 0, 100, 1000, 759, 75}, // mid-values
                    {150, 0, 100, 1000, 650, 50}, {333, 0, 100, 1000, 699, 36}, {459, 0, 100, 1000, 1000, 54}, // mid-v
                    {0, 0, 100, 1000, 9, 0}, {0, 0, 100, 1000, 1200, 100} // values on/over the limits
            });

            List<Object[]> increasing_negative = Arrays.asList(new Object[][]{
                    {0, -50, 50, 1000, 500, 0}, {0, -50, 50, 1000, 250, -25}, {0, -50, 50, 1000, 750, 25}, // mid-v
            });

            List<Object[]> decreasing = Arrays.asList(new Object[][]{
                    {0, 100, 0, 1000, 500, 50}, {0, 100, 0, 1000, 300, 70} // mid-values
            });

            List<Object[]> decreasing_negative = Arrays.asList(new Object[][]{
                    {0, 50, -50, 1000, 500, 0}, {0, 50, -50, 1000, 250, 25}, {0, 50, -50, 1000, 750, -25}, // mid-v
            });

            List<Object[]> all = new ArrayList<>();
            all.addAll(increasing);
            all.addAll(increasing_negative);
            all.addAll(decreasing);
            all.addAll(decreasing_negative);

            return all;
        }

        @Test
        public void Test() {
            // ARRANGE
            ClockMock c = new ClockMock(pClockStart);
            GraduallyChangeable.setClock(c);
            var gc = new GraduallyChangeable();
            gc.startNew(pFrom, pTo, pMilliseconds);
            c.millisToReturn = pCallTime;
            GraduallyChangeable.setClock(c);

            // ACT
            int result = gc.getCurrentValue();

            // ASSERT
            Assert.assertEquals(pExpected, result);

        }
    }

    public static class NonParameterizedPart {

        @Test
        public void InitByConstructor() {
            GraduallyChangeable g = new GraduallyChangeable();

            Assert.assertNotNull(g);
        }

        @Test
        public void ElapsedTime() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

            // ARRANGE
            int pFrom = 0;
            int pTo = 10;
            int pMilliseconds = 100;
            int pClockStart = 0;
            int pClockElapsed = 10;

            ClockMock c = new ClockMock(pClockStart);
            GraduallyChangeable.setClock(c);
            GraduallyChangeable gc = new GraduallyChangeable();
            gc.startNew(pFrom, pTo, pMilliseconds);
            c.millisToReturn = pClockElapsed;
            GraduallyChangeable.setClock(c);
            Method m = GraduallyChangeable.class.getDeclaredMethod("getConvertedElapsedTime");
            m.setAccessible(true);

            // ACT
            int result = (int) m.invoke(gc, (Object[]) null);

            // ASSERT
            Assert.assertEquals(pClockElapsed - pClockStart, result);
        }

        @Test
        public void ElapsedTimeOverLimit() throws NoSuchMethodException, InvocationTargetException,
                IllegalAccessException {

            // ARRANGE
            int pFrom = 0;
            int pTo = 10;
            int pMilliseconds = 100;
            int pClockStart = 0;
            int pClockElapsed = 1000;

            ClockMock c = new ClockMock(pClockStart);
            GraduallyChangeable.setClock(c);
            GraduallyChangeable gc = new GraduallyChangeable();
            gc.startNew(pFrom, pTo, pMilliseconds);
            c.millisToReturn = pClockElapsed;
            GraduallyChangeable.setClock(c);
            Method m = GraduallyChangeable.class.getDeclaredMethod("getConvertedElapsedTime");
            m.setAccessible(true);

            // ACT
            int result = (int) m.invoke(gc, (Object[]) null);

            // ASSERT
            Assert.assertEquals(pMilliseconds, result);
        }

        void RangeDetermination(int pFrom, int pTo, int expectedRange, int pMilliseconds) throws
                NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            // ASSERT
            GraduallyChangeable gc = new GraduallyChangeable();
            Method m = GraduallyChangeable.class.getDeclaredMethod("getRange", int.class, int.class);
            m.setAccessible(true);

            // ACT
            int result = (int) m.invoke(gc, new Object[]{pFrom, pTo});

            // ASSERT
            Assert.assertEquals(expectedRange, result);
        }

        @Test
        public void RangeDetermination_Increasing() throws NoSuchMethodException, InvocationTargetException,
                IllegalAccessException {
            int pFrom = 0;
            int pTo = 10;
            int expectedRange = 10;
            int pMilliseconds = 100;

            RangeDetermination(pFrom, pTo, expectedRange, pMilliseconds);
        }

        @Test
        public void RangeDetermination_Decreasing() throws NoSuchMethodException, InvocationTargetException,
                IllegalAccessException {

            int pFrom = 50;
            int pTo = 10;
            int expectedRange = 40;
            int pMilliseconds = 100;

            RangeDetermination(pFrom, pTo, expectedRange, pMilliseconds);
        }

        @Test
        public void RangeDetermination_NegativePositive() throws NoSuchMethodException, InvocationTargetException,
                IllegalAccessException {
            int pFrom = -20;
            int pTo = 30;
            int expectedRange = 50;
            int pMilliseconds = 100;

            RangeDetermination(pFrom, pTo, expectedRange, pMilliseconds);
        }

        @Test
        public void RangeDetermination_NegativeNegative() throws NoSuchMethodException, InvocationTargetException,
                IllegalAccessException {
            int pFrom = 50;
            int pTo = 10;
            int expectedRange = 40;
            int pMilliseconds = 100;

            RangeDetermination(pFrom, pTo, expectedRange, pMilliseconds);
        }

        @Test
        public void DetermineChange_Zero() throws NoSuchMethodException, InvocationTargetException,
                IllegalAccessException {
            GraduallyChangeable gc = new GraduallyChangeable();
            gc.startNew(0, 0, 0);
            Method m = GraduallyChangeable.class.getDeclaredMethod("determineChange");
            m.setAccessible(true);

            int res = (int) m.invoke(gc, new Object[]{});

            Assert.assertEquals(0, res);
        }

        @Test
        public void DetermineChange() throws NoSuchMethodException, InvocationTargetException,
                IllegalAccessException {
            GraduallyChangeable.setClock(new ClockMock(0));
            GraduallyChangeable gc = new GraduallyChangeable();
            gc.startNew(0, 500, 1000);
            GraduallyChangeable.setClock(new ClockMock(500));
            Method m = GraduallyChangeable.class.getDeclaredMethod("determineChange");
            m.setAccessible(true);

            int res = (int) m.invoke(gc, new Object[]{});

            Assert.assertEquals(250, res);
        }
    }
}

class ClockMock extends Clock {

    long millisToReturn;

    ClockMock(long millisToReturn) {
        this.millisToReturn = millisToReturn;
    }

    @Override
    public ZoneId getZone() {
        return null;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return null;
    }

    @Override
    public Instant instant() {
        return null;
    }

    @Override
    public long millis() {
        return millisToReturn;
    }
}

