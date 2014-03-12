import org.testng.Assert;
import org.testng.annotations.Test;

public class PalindromeTest
{
    @Test(description="Test a valid palindrome")
    public void testPalindrome()
    {
        String phrase = "redrummurder";
        Assert.assertTrue(Palindrome.isPalindrome(phrase));
    }

    @Test(description="Test an invalid palindrome")
    public void testNotPalindrome()
    {
        String phrase = "Not a palindrome";
        Assert.assertTrue(Palindrome.isPalindrome(phrase) == false);
    }
}