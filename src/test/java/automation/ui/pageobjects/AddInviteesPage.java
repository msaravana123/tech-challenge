package automation.ui.pageobjects;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddInviteesPage extends MobilePageObject {

    public AddInviteesPage(WebDriver driver) {
        super(driver);
    }

    @iOSXCUITFindBy(id = "Add Invitees")
    public WebElement addInviteesTitle;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
    public WebElement toField;

    @iOSXCUITFindBy(id = "Done")
    public WebElement doneButton;
}
