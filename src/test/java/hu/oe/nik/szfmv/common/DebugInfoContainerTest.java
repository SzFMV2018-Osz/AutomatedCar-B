package hu.oe.nik.szfmv.common;

import org.junit.Assert;
import org.junit.Test;

public class DebugInfoContainerTest {
    @Test
    public void testCorrectValuesAfterConstruction() {
        DebugInfoContainer dic = new DebugInfoContainer(2, 3);
        Assert.assertEquals(dic.getCarX(), 2);
        Assert.assertEquals(dic.getCarY(), 3);
    }
}