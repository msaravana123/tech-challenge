package automation.ui.pageobjects;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EndRepeatPage extends MobilePageObject {

    @iOSXCUITFindBy(id = "End Repeat")
    public WebElement repeatTitle;

    @iOSXCUITFindBy(id = "On Date")
    public WebElement onDate;

    @iOSXCUITFindBy(id = "Next Month")
    public WebElement nextMonth;

    @iOSXCUITFindBy(id = "New Event")
    public WebElement newEvent;

    public EndRepeatPage(WebDriver driver) {
        super(driver);
    }
}
