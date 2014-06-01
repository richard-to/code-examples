import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RunawayTest extends AbstractOutputTest
{
    @Test(description="Test that correct output is printed to console")
    public void testRunawayComments()
    {
        ByteArrayOutputStream streamOut = captureOut();
        
        String[] args = {};        
        Runaway.main(args);
        String output = streamOut.toString();

        revertStreams();

        Assert.assertEquals(output, "Hello World\nHello Universe\n");
    }
}