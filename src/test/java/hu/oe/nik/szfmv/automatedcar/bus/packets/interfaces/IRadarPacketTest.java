package hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces;

import hu.oe.nik.szfmv.environment.worldobjectclasses.Collidable;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IRadarPacketTest {
    @Test
    public int getLaneNumber() {
        return 0;
    }

    @Test
    @Parameters({"111","1234"})
    public void setLaneNumber(int laneNumber) {
    }

    @Test
    public Collidable getRightClosesObject() {
        return null;
    }

    @Test
    public Collidable getLeftClosesObject() {
        return null;
    }

    @Test
    public Collidable getActualLaneClosesObjects() {
        return null;
    }
}