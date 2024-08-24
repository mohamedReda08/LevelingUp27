package testPackage.linear;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TestMozilla {
    WebDriver firefoxDriver;
    String google = "https://www.google.com";
    @BeforeTest
    public void setup()
    {
         firefoxDriver = new FirefoxDriver();
    }
    @Test
    public void testSearchForTestNG()
    {
        firefoxDriver.navigate().to(google);
        WebElement searchBox = firefoxDriver.findElement(By.className("gLFyf"));
        searchBox.sendKeys("TestNG");
        WebElement searchButton = firefoxDriver.findElement(By.className("gNO89b"));
        searchButton.submit();

        Wait<WebDriver> wait = new WebDriverWait(firefoxDriver, Duration.ofSeconds(30));
        List<WebElement> searchResults = firefoxDriver.findElements(By.cssSelector("h3"));
//        wait.until();
        System.out.println(searchResults.toArray().length);
        WebElement searchResult = searchResults.get(3);

        Assert.assertEquals(searchResult.getText(), "TestNG Tutorial");
        System.out.println(searchResult.getText());
    }

//    @AfterTest
//    public void afterTest()
//    {
//        firefoxDriver.quit();
//    }
}
