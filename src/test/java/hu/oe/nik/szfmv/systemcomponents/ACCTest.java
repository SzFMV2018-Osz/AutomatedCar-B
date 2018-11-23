package hu.oe.nik.szfmv.systemcomponents;

import hu.oe.nik.szfmv.automatedcar.systemcomponents.ACC;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ACCTest {
    private ACC automat;

    @Before
    public void Setup(){
      automat = new ACC();

    }

    @Test
    @Parameters({"false|false|true", "true|false|false", "true|true|false", "false|true|false"})
    public void TestBrakingDisable(boolean manual, boolean aeb, boolean expectedState){
        automat.setActive(true);

        automat.handleBrakingState(manual, aeb);

        Assert.assertEquals(automat.isActive(), expectedState);
    }
}
