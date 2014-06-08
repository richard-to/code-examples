import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PizzaSlicerTest extends AbstractOutputTest
{
    @Test(description="Test 20 x 5 pizza for 7 people")
    public void testPizzaSlicer1()
    {
        validateConsoleOutput(20, 5, 7, 0.014285714285714287);
    }

    @Test(description="Test 1 x 2 pizza for 30 people")
    public void testPizzaSlicer2()
    {
        validateConsoleOutput(1, 2, 30, 6.666666666666667E-5);
    }

    @Test(description="Test 400 x 20 pizza for 4 people")
    public void testPizzaSlicer3 ()
    {
        validateConsoleOutput(400, 20, 4, 2);
    }

    public void validateConsoleOutput(int length, int width, int size, double expected)
    {
        ByteArrayOutputStream streamOut = captureOut();

        PizzaSlicer.slice(length, width, size);
        String output = streamOut.toString();

        revertStreams();

        Assert.assertEquals(output.trim(), Double.toString(expected));
    }
}