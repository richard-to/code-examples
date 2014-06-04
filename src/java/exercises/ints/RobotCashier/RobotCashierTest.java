import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RobotCashierTest extends AbstractOutputTest
{
    @Test(description="Test total for a family of 4")
    public void testFamilyOfFour()
    {
        validateConsoleOutput(4, 40);
    }

    @Test(description="Test total for a family of 24")
    public void testDiscount11()
    {
        validateConsoleOutput(24, 240);
    }

    @Test(description="Test total for a family of 1")
    public void testFamilyOfOne()
    {
        validateConsoleOutput(1, 15);
    }

    public void validateConsoleOutput(int input, int expected)
    {
        ByteArrayOutputStream streamOut = captureOut();
        
        RobotCashier.calculate(input);
        String output = streamOut.toString();

        revertStreams();

        Assert.assertEquals(output.trim(), Integer.toString(expected));
    }            
}