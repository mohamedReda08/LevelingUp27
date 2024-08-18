package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;


public class BasicTest {

    private WebDriver driver;
    String url = "https://www.google.com/";

    @BeforeTest
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("webSocketUrl", true);
        driver = new ChromeDriver(options);
    }
    @Test
    public void testPageTitle(){
            driver.get(url);
            String title = driver.getTitle();
            Assert.assertEquals(title, "Google");
            driver.quit();
    }
    @Test
    public void testGoogleLogo() throws IOException {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        driver.get(url);
//        WebElement logo = driver.findElement(By.className("lnXdpd"));
            WebElement logo = driver.findElement(By.xpath("//img.lnXdpd"));
//        Assert.assertTrue(logo.isDisplayed());
        Assert.assertTrue(logo.isDisplayed());
        String screenshot = browsingContext.captureScreenshot();
        byte[] imgByteArray = Base64.getDecoder().decode(screenshot);
        FileOutputStream imgOutFile = new FileOutputStream("screenshot_googleLogo.png");
        imgOutFile.write(imgByteArray);
        imgOutFile.close();
        driver.quit();
    }

    @Test
    public void testSearchSelenium() {

        driver.get(url);
        WebElement searchBox = driver.findElement(By.className("gLFyf"));
        searchBox.sendKeys("Selenium WebDriver");
        driver.findElement(By.name("btnK")).click();
        WebElement searchResults = driver.findElements(By.id("rso")).getFirst();
        Assert.assertEquals(searchResults.toString(), "Selenium - Web Browser Automation");
        System.out.println(searchResults.getText());

    }
@AfterTest
    public void afterTest(){
    driver.quit();
}

}
