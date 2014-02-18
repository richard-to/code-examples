import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Variables1Test
{
    @Test
    public void testGreetingVariable()
    {
        Variables1 variables1 = new Variables1();
        assertEquals(variables1.getGreeting(), "Hello");
    }
}