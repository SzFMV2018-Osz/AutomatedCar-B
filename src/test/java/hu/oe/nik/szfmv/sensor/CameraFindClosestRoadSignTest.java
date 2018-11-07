package hu.oe.nik.szfmv.sensor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.oe.nik.szfmv.automatedcar.bus.VirtualFunctionBus;
import hu.oe.nik.szfmv.automatedcar.sensor.Camera;
import hu.oe.nik.szfmv.environment.WorldObject;
import hu.oe.nik.szfmv.environment.worldobjectclasses.Human;
import hu.oe.nik.szfmv.environment.worldobjectclasses.RoadSign;

public class CameraFindClosestRoadSignTest {

    private Camera underTest;

    @Before
    public void setUp() {
        underTest = new Camera(new VirtualFunctionBus());
    }

    @Test
    public void testFindClosestRoadSignShouldReturnEmptyWhenEmptyListIsInput() {
        // GIVEN
        List<WorldObject> param = new ArrayList<>();
        // WHEN
        Optional<WorldObject> result = underTest.findClosestRoadSign(param);
        // THEN
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testFindClosestRoadSignShouldReturnEmptyWhenNoRoadSignIsRange() {
        // GIVEN
        List<WorldObject> param = Arrays.asList(new Human(0, 0, "man.png"));
        // WHEN
        Optional<WorldObject> result = underTest.findClosestRoadSign(param);
        // THEN
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testFindClosestRoadSignShouldRoadSignWhenRoadSignIsInRange() {
        // GIVEN
        RoadSign expectedRoadSign = new RoadSign(0, 0, "roadsign_speed_40.png");
        List<WorldObject> param = Arrays.asList(new Human(0, 0, "man.png"), expectedRoadSign);
        // WHEN
        Optional<WorldObject> result = underTest.findClosestRoadSign(param);
        // THEN
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(expectedRoadSign.getX(), result.get().getX());
        Assert.assertEquals(expectedRoadSign.getY(), result.get().getY());
        Assert.assertEquals(expectedRoadSign.getImageFileName(), result.get().getImageFileName());
    }

    @Test
    public void testFindClosestRoadSignShouldFindClosestWhenMultipleRoadSignsInRange() {
        // GIVEN
        RoadSign expectedRoadSign = new RoadSign(0, 0, "roadsign_speed_40.png");
        List<WorldObject> param = Arrays.asList(new Human(0, 0, "man.png"), new RoadSign(1, 1, "roadsign_speed_40.png"),
                expectedRoadSign);
        // WHEN
        Optional<WorldObject> result = underTest.findClosestRoadSign(param);
        // THEN
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(expectedRoadSign.getX(), result.get().getX());
        Assert.assertEquals(expectedRoadSign.getY(), result.get().getY());
        Assert.assertEquals(expectedRoadSign.getImageFileName(), result.get().getImageFileName());
    }
}
