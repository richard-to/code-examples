import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MysteryMethodTest extends AbstractOutputTest
{
    @Test(description="Test that correct method is called")
    public void testMain()
    {
        ByteArrayOutputStream streamOut = captureOut();

        String[] args = {};
        MysteryMethod.main(args);
        String output = streamOut.toString();

        revertStreams();

        Assert.assertEquals(output, "ESREVINu OLLEh");
    }
}