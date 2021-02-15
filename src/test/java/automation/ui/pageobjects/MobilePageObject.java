package automation.ui.pageobjects;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MobilePageObject extends PageObject {

    protected WebDriverWait wait;

    public MobilePageObject(final WebDriver driver) {
        super(driver);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wait = new WebDriverWait(getDriver(), 30);

    }
}
