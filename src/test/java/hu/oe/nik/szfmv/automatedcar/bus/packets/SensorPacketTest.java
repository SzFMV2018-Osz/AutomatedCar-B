package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.environment.WorldObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SensorPacketTest {
    @Test
    public void InitTest() {
        SensorPacket sp = new SensorPacket(new ArrayList<>(), new ArrayList<>());
        Assert.assertNotNull(sp);
    }

    @Test
    public void ReadTest() {
        ArrayList<WorldObject> worldObjects = new ArrayList<>();
        worldObjects.add(new WorldObject(0, 0, null));
        SensorPacket sp = new SensorPacket(worldObjects, new ArrayList<>());

        List<WorldObject> collidable = sp.getDetectedCollidableObjects();
        List<WorldObject> nonCollidable = sp.getDetectedNonCollidableObjects();

        Assert.assertEquals(worldObjects.size(), collidable.size());
        Assert.assertEquals(worldObjects.get(0).getX(), collidable.get(0).getX());
        Assert.assertEquals(worldObjects.get(0).getY(), collidable.get(0).getY());
        Assert.assertEquals(worldObjects.get(0).getImageFileName(), collidable.get(0).getImageFileName());
        Assert.assertTrue(nonCollidable.isEmpty());

    }
}
