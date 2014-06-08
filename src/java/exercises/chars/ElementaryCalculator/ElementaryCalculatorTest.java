import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ElementaryCalculatorTest extends AbstractOutputTest
{
    @Test(description="Add 9.05 + 7.0")
    public void test0()
    {
        validateConsoleOutput('9', '5', '7', '0', 16.05);
    }

    @Test(description="Add 0.0 + 2.08")
    public void test1()
    {
        validateConsoleOutput('0', '0', '2', '8', 2.08);
    }

    @Test(description="Add 1.09 + 9.09")
    public void test2()
    {
        validateConsoleOutput('1', '9', '9', '9', 10.18);
    }

    @Test(description="Add 0.05 + 9.0")
    public void test3()
    {
        validateConsoleOutput('0', '5', '9', '0', 9.05);
    }


    public void validateConsoleOutput(char int1, char frac1, char int2, char frac2, double expected)
    {
        ByteArrayOutputStream streamOut = captureOut();

        ElementaryCalculator.add(int1, frac1, int2, frac2);
        String output = streamOut.toString();

        revertStreams();

        Assert.assertEquals(output.trim(), Double.toString(expected));
    }
}