import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public abstract class AbstractOutputTest
{
    public ByteArrayOutputStream captureOut()
    {
        ByteArrayOutputStream streamOut = new ByteArrayOutputStream();        
        PrintStream psOut = new PrintStream(streamOut);
        System.setOut(psOut);
        return streamOut;
    }

    public ByteArrayOutputStream captureErr()
    {
        ByteArrayOutputStream streamErr = new ByteArrayOutputStream();        
        PrintStream psErr = new PrintStream(streamErr);
        System.setOut(psErr);
        return streamErr;
    }

    public void revertStreams()
    {
        PrintStream psOut = new PrintStream(new FileOutputStream(FileDescriptor.out));
        PrintStream psErr = new PrintStream(new FileOutputStream(FileDescriptor.err));
        System.setOut(psOut);
        System.setErr(psErr);
    }

}