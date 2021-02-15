package automation.ui.pageobjects;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EventDetailsPage extends MobilePageObject{

    @iOSXCUITFindBy(id = "Event Details")
    public WebElement eventDetailsTitle;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[contains(@name,'Invitees')]")
    public WebElement invitees;


    public EventDetailsPage(WebDriver driver) {
        super(driver);
    }
}
