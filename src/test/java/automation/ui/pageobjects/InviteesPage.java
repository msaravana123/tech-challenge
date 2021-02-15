package automation.ui.pageobjects;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InviteesPage extends MobilePageObject{

    @iOSXCUITFindBy(id = "Invitees")
    public WebElement inviteesTitle;

    @iOSXCUITFindBy(id = "New Event")
    public WebElement newEventButton;

    public InviteesPage(WebDriver driver) {
        super(driver);
    }
}
