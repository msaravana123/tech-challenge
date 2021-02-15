package automation.ui.actions;

import automation.ui.pageobjects.CalendarHome;
import io.appium.java_client.MobileBy;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

public class CalendarHomeActions extends UIInteractionSteps {

    CalendarHome calendarHome;

    @Step
    public void launchCalendatApp() {
        waitFor(calendarHome.calendarsButton);
    }

    @Step
    public void tapAddButton() {
        waitFor(calendarHome.addButton);
        clickOn(calendarHome.addButton);
    }

    @Step
    public void tapOnEvent(String event) {
        waitFor(calendarHome.calendarsButton);
        clickOn(getDriver().findElement(MobileBy.xpath("//XCUIElementTypeCell[contains(@name, '" + event + "')]")));
    }
}
