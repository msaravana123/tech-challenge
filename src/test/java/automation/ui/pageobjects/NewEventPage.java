package automation.ui.pageobjects;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewEventPage extends MobilePageObject{
    @iOSXCUITFindBy(id = "New Event")
    public WebElement newEventTitle;

    @iOSXCUITFindBy(id = "Cancel")
    public WebElement cancelButton;

    @iOSXCUITFindBy(id = "Title")
    public WebElement titleTextBox;

    @iOSXCUITFindBy(id = "Starts")
    public WebElement startDate;

    @iOSXCUITFindBy(id = "Time")
    public WebElement time;

    @iOSXCUITFindBy(id = "am")
    public WebElement am;

    @iOSXCUITFindBy(id = "pm")
    public WebElement pm;

    @iOSXCUITFindBy(id = "Ends")
    public WebElement endDate;

    @iOSXCUITFindBy(id = "Repeat")
    public WebElement startRepeat;

    @iOSXCUITFindBy(id = "End Repeat")
    public WebElement endRepeat;

    @iOSXCUITFindBy(id="Invitees")
    public WebElement invitees;

    @iOSXCUITFindBy(id="Add")
    public WebElement addButton;

    public NewEventPage(WebDriver driver) {
        super(driver);
    }
}
