import org.testng.Assert;
import org.testng.annotations.Test;

public class Ex1Test
{
    @Test(description="Test greeting variable contains 'Hello'")
    public void testGreetingVariable()
    {
        Ex1 ex1 = new Ex1();
        Assert.assertEquals(ex1.getGreeting(), "Hello");
    }
}