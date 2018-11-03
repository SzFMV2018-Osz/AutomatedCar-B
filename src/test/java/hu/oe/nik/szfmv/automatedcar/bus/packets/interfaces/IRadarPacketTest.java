package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

import hu.oe.nik.szfmv.environment.worldobjectclasses.Collidable;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class IRadarPacketTest {
    @Ignore("Ignore")
    @Test
    public int getLaneNumber() {
        return 0;
    }
    @Ignore("Ignore")
    @Test
    @Parameters({"111","1234"})
    public void setLaneNumber(int laneNumber) {
    }
    @Ignore("Ignore")
    @Test
    public Collidable getRightClosesObject() {
        return null;
    }
    @Ignore("Ignore")
    @Test
    public Collidable getLeftClosesObject() {
        return null;
    }
    @Ignore("Ignore")
    @Test
    public Collidable getActualLaneClosesObjects() {
        return null;
    }
}