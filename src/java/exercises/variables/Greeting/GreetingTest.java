import org.testng.Assert;
import org.testng.annotations.Test;

public class GreetingTest
{
    @Test(description="Test greeting variable contains 'Hello'")
    public void testGreetingVariable()
    {
        Greeting greeting = new Greeting();
        Assert.assertEquals(greeting.getGreeting(), "Hello");
    }
}