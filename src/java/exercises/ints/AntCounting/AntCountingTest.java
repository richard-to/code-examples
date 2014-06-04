import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AntCountingTest extends AbstractOutputTest
{
    @Test(description="Test an input of 2023253413 ants")
    public void testAnts1()
    {
        validateConsoleOutput(2023253413, 124230234);
    }

    @Test(description="Test an input of 1023383883 ants")
    public void testAnts2()
    {
        validateConsoleOutput(1023383883, 1124099764);
    } 
    
    @Test(description="Test an input of 0 ants")
    public void testAnts3()
    {
        validateConsoleOutput(0, 2147483647);
    }

    @Test(description="Test a full box of ants")
    public void testAnts4()
    {
        validateConsoleOutput(2147483647, 0);
    }

    public void validateConsoleOutput(int input, int expected)
    {
        ByteArrayOutputStream streamOut = captureOut();
        
        AntCounting.checkDifference(input);
        String output = streamOut.toString();

        revertStreams();

        Assert.assertEquals(output.trim(), Integer.toString(expected));
    }
}