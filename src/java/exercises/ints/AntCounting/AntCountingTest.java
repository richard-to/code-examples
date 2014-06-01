import org.testng.Assert;
import org.testng.annotations.Test;

public class AntCountingTest
{
    @Test(description="Test an input of 2023253413 ants")
    public void testAnts1()
    {
        Assert.assertEquals(AntCounting.checkDifference(2023253413), 124230234);
    }

    @Test(description="Test an input of 1023383883 ants")
    public void testAnts2()
    {
        Assert.assertEquals(AntCounting.checkDifference(1023383883), 1124099764);
    } 
    
    @Test(description="Test an input of 0 ants")
    public void testAnts3()
    {
        Assert.assertEquals(AntCounting.checkDifference(0), 2147483647);
    }

    @Test(description="Test a full box of ants")
    public void testAnts4()
    {
        Assert.assertEquals(AntCounting.checkDifference(2147483647), 0);
    }
}