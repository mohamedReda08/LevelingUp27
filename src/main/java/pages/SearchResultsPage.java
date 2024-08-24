package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage {
    WebDriver driver;

    public By resultLocator(int index) {
        return By.xpath("(//article)["+ index+"]//h2/a");
    }



   public SearchResultsPage (WebDriver driver)
    {
        this.driver = driver;
    }

    public WebElement getResult(int index) {
       return driver.findElement(resultLocator(index));

    }
}
