package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IRadarPacket;
import junitparams.Parameters;
import org.junit.Test;

import static org.junit.Assert.*;

public class RadarPacketTest {

    private IRadarPacket radarTest=new RadarPacket();

    @Test
    @Parameters({"100","50"})
    public void setLaneNumber(int laneNumber) {
        radarTest.setLaneNumber(laneNumber);
    }
}