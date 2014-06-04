import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CouponsTest extends AbstractOutputTest
{
    @Test(description="Test coupon number 12345677")
    public void testCoupon12345677()
    {
        validateConsoleOutput(12345677, 2);
    }

    @Test(description="Test coupon number 12345678")
    public void testCoupon12345678()
    {
        validateConsoleOutput(12345678, 1);
    }

    @Test(description="Test total for a family of 123456")
    public void testCoupon123456()
    {
        validateConsoleOutput(123456, 0);
    }

    @Test(description="Test total for a family of 1234567")
    public void testCoupon1234567()
    {
        validateConsoleOutput(1234567, 1);
    }

    public void validateConsoleOutput(int input, int expected)
    {
        ByteArrayOutputStream streamOut = captureOut();
        
        Coupons.validate(input);
        String output = streamOut.toString();

        revertStreams();

        Assert.assertEquals(output.trim(), Integer.toString(expected));
    }                  
}