import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HelloUniverseTest extends AbstractOutputTest
{
    @Test(description="Test that 'I, for one, welcome our new alien overlords' is printed to console")
    public void testOutput()
    {
        ByteArrayOutputStream streamOut = captureOut();
        
        String[] args = {};        
        HelloUniverse.main(args);
        String output = streamOut.toString();

        revertStreams();

        Assert.assertEquals(output, "I, for one, welcome our new alien overlords");
    }
}