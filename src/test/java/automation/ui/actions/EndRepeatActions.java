package automation.ui.actions;

import automation.ui.pageobjects.EndRepeatPage;
import io.appium.java_client.MobileBy;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

import java.time.LocalDate;

public class EndRepeatActions extends UIInteractionSteps {

    EndRepeatPage endRepeat;

    @Step
    public void setEndDate(int months, LocalDate date) {
        waitFor(endRepeat.repeatTitle);
        clickOn(endRepeat.onDate);
        for(int i=0;i<months;i++) {
            clickOn(endRepeat.nextMonth);
            waitABit(2000);
        }

        getDriver().findElement(MobileBy.AccessibilityId(String.valueOf(date.getDayOfMonth()))).click();
        clickOn(endRepeat.newEvent);
    }
}
