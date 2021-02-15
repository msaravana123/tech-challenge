package automation.ui.pageobjects;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RepeatPage extends MobilePageObject{

    @iOSXCUITFindBy(id = "Repeat")
    public WebElement repeatTitle;

    @iOSXCUITFindBy(id = "Every Week")
    public WebElement everyWeek;

    @iOSXCUITFindBy(id = "Every Fortnight")
    public WebElement everyFortnight;

    @iOSXCUITFindBy(id = "Every Month")
    public WebElement everyMonth;

    @iOSXCUITFindBy(id = "Every Year")
    public WebElement everyYear;

    public RepeatPage(WebDriver driver) {
        super(driver);
    }
}
