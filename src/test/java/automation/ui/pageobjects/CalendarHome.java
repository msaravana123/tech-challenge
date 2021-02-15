package automation.ui.pageobjects;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalendarHome extends MobilePageObject {

    @iOSXCUITFindBy(id = "Add")
    public WebElement addButton;

    @iOSXCUITFindBy(id = "Calendars")
    public WebElement calendarsButton;

    public CalendarHome(WebDriver driver) {
        super(driver);
    }
}
