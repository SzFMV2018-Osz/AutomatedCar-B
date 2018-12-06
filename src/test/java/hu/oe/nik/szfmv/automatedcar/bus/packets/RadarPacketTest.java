package hu.oe.nik.szfmv.automatedcar.bus.packets;

import hu.oe.nik.szfmv.automatedcar.bus.packets.interfaces.IRadarPacket;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(JUnitParamsRunner.class)
public class RadarPacketTest {
   private IRadarPacket radarTest;

   @Before
   public  void Setup(){
       radarTest=new RadarPacket();
   }
    @Test
    @Parameters({ "100","10","0" })
    public void setLaneNumber(int laneNumber) {

        radarTest.setLaneNumber(laneNumber);
    }
}