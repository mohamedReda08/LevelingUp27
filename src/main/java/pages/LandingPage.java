package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LandingPage {
    WebDriver driver;
    String url = "https://duckduckgo.com/";
    By searchBox = By.xpath("//input[@id ='searchbox_input']");

    //    Create Constructor
    public  LandingPage (WebDriver driver){
    this.driver = driver;
    }


    public void navigate() {
        driver.navigate().to(url);
    }


    public void search(String searchText)
    {
        driver.findElement(searchBox).sendKeys(searchText + Keys.ENTER);
    }
    
    
}
