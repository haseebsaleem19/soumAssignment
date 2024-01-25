package general;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CommonAssertions {
    private final WebDriver driver;
    public CommonAssertions(WebDriver driver) {
        this.driver = driver;
    }

    public static void assertion(int actual,int expected)
    {
        Assert.assertEquals(actual,expected);
    }
}
