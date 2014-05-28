import org.testng.Assert;
import org.testng.annotations.Test;

public class Ex2Test
{
    @Test(description="Test number1 is greater than 100")
    public void testNumber1Value()
    {
        Ex2 ex2 = new Ex2();
        Assert.assertTrue(ex2.getNumber1() > 100);
    }

    @Test(description="Test number2 is greater than 100")
    public void testNumber2Value()
    {
        Ex2 ex2 = new Ex2();
        Assert.assertTrue(ex2.getNumber2() > 100);
    }

    @Test(description="Test the product of number1 and number2 is correct")
    public void testProduct()
    {
        Ex2 ex2 = new Ex2();
        Assert.assertEquals(
            ex2.getNumber1() * ex2.getNumber2(), ex2.getProduct());
    }
}