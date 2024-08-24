package testPackage.linear;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;


public class BasicTest {

    private WebDriver driver;
    String url = "https://www.google.com/";

//  Initiating ChromeOptions
    @BeforeTest
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
    }

//    Testing Page Title
    @Test
    public void testPageTitle(){
            driver.get(url);
            String title = driver.getTitle();
            Assert.assertEquals(title, "Google");
    }

//  Testing Google Logo is displayed on Google home screen with a screenshot
    @Test
    public void testGoogleLogo() throws IOException {
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        driver.get(url);
        WebElement logo = driver.findElement(By.className("lnXdpd"));
        Assert.assertTrue(logo.isDisplayed());
        String screenshot = browsingContext.captureScreenshot();
        byte[] imgByteArray = Base64.getDecoder().decode(screenshot);
        FileOutputStream imgOutFile = new FileOutputStream("screenshot_googleLogo.png");
        imgOutFile.write(imgByteArray);
        imgOutFile.close();
    }

//  Test Searching for "Selenium WebDriver on Google and verifying the first search result.
    @Test
    public void testSearchSelenium() {

        driver.get(url);
        WebElement searchBox = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement searchResult = driver.findElement(By.cssSelector("h3"));
        wait.until(d -> searchResult.isDisplayed());
        String text = searchResult.getText();
//        Assert.assertEquals(text, "Selenium - Web Browser Automation", "Expected result does not match");
        System.out.println(text);
    }


//  Quitting driver after completing test execution
    @AfterTest
    public void afterTest(){
    driver.quit();
    }

}
