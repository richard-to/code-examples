import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest
{
    @Test(description="Test number1 is greater than 100")
    public void testNumber1Value()
    {
        Product product = new Product();
        Assert.assertTrue(product.getNumber1() > 100);
    }

    @Test(description="Test number2 is greater than 100")
    public void testNumber2Value()
    {
        Product product = new Product();
        Assert.assertTrue(product.getNumber2() > 100);
    }

    @Test(description="Test the product of number1 and number2 is correct")
    public void testProduct()
    {
        Product product = new Product();
        Assert.assertEquals(
            product.getNumber1() * product.getNumber2(), product.getProduct());
    }
}