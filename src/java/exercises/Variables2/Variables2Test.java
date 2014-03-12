import org.testng.Assert;
import org.testng.annotations.Test;

public class Variables2Test
{
    @Test(description="Test number 1 is greater than 100")
    public void testNumber1Value()
    {
        Variables2 variables = new Variables2();
        Assert.assertTrue(variables.getNumber1() > 100);
    }

    @Test(description="Test number 1 is greater than 100")
    public void testNumber2Value()
    {
        Variables2 variables = new Variables2();
        Assert.assertTrue(variables.getNumber2() > 100);
    }

    @Test(description="Test the product of number 1 and number 2 is correct")
    public void testProduct()
    {
        Variables2 variables = new Variables2();
        Assert.assertEquals(
            variables.getNumber1() * variables.getNumber2(), variables.getProduct());
    }
}