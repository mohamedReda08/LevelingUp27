package testPackage.modular;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LandingPage;
import pages.SearchResultsPage;

import java.time.Duration;

public class Task24Aug {
     WebDriver driver;
     Wait<WebDriver> wait;
    LandingPage landingPage;
    SearchResultsPage searchResultsPage;

    String searchText = "Selenium WebDriver";
    /** ________________ Basic ________________
 *
 * Open Mozilla Firefox
 * Navigate to [https://duckduckgo.com/]
 * Search for [Selenium WebDriver]
 * Assert that the link of the first result is [https://www.selenium.dev/documentation/webdriver/]
 * Close Mozilla Firefox
 **/

    @BeforeMethod
    public void setup()
    {
        driver = new FirefoxDriver();
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NotFoundException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(TimeoutException.class)
                .ignoring(NoSuchElementException.class)
                .withMessage("Test Error message")
        ;
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1280,720));
//        Assigning value to landingPage object
        landingPage = new LandingPage(driver);
        landingPage.navigate();
        searchResultsPage = new SearchResultsPage(driver);

    }

    @Test
    public void testSeleniumSearch()
    {

        landingPage.search(searchText);
        wait.until((WebDriver d) -> {
            String url = searchResultsPage.getResult(1).getAttribute("href");
           Assert.assertNotEquals(url, null);
           System.out.println("The Url found is " +url);
           return true;

        });
    }
/*
 *#4
 * ________________ Moderate ________________
 * Open Mozilla Firefox
 * Navigate to [https://duckduckgo.com/]
 * Search for [TestNG]
 * Assert that the text of the fourth result is [TestNG Tutorial]
 * Close Mozilla Firefox
 *
 */
    @Test
    public void search4thResult()
    {

        landingPage.search("TestNG");

        wait.until(d-> {
            String text = searchResultsPage.getResult(4).getText();
            System.out.println("The Text found is " +text);
            Assert.assertNotEquals(text, null);
            Assert.assertEquals(text, "TextNG Tutorial");
            System.out.println(text);
            return true;

        });
    }

    @AfterMethod
        public void afterTest()
    {
        driver.quit();
    }
}
