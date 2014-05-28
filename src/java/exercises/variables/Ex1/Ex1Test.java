import org.testng.Assert;
import org.testng.annotations.Test;

public class Variables1Test
{
    @Test(description="Test greeting variable contains 'Hello'")
    public void testGreetingVariable()
    {
        Variables1 variables1 = new Variables1();
        Assert.assertEquals(variables1.getGreeting(), "Hello");
    }
}