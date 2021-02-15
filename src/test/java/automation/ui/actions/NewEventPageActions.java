package automation.ui.actions;

import automation.ui.pageobjects.NewEventPage;
import io.appium.java_client.MobileBy;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

import java.time.LocalDate;

public class NewEventPageActions extends UIInteractionSteps {

    NewEventPage newEventPage;

    @Step
    public void setMeetingTitle(String title) {
        waitFor(newEventPage.newEventTitle);
        typeInto(newEventPage.titleTextBox, title);
    }

    @Step
    public void setStartTime(LocalDate date, String time) {
        String[] splitTime = time.split("\\s+");
        clickOn(newEventPage.startDate);
        getDriver().findElement(MobileBy.AccessibilityId(String.valueOf(date.getDayOfMonth()))).click();

        typeInto(newEventPage.time, splitTime[0]);

        if(splitTime[1].equalsIgnoreCase("am")) {
            clickOn(newEventPage.am);
        } else if(splitTime[1].equalsIgnoreCase("pm")) {
            clickOn(newEventPage.pm);
        }

        clickOn(newEventPage.startDate);

    }

    @Step
    public void endStartTime(String time) {
        String[] splitTime = time.split("\\s+");
        clickOn(newEventPage.endDate);
        typeInto(newEventPage.time, splitTime[0]);

        if(splitTime[1].equalsIgnoreCase("am")) {
            clickOn(newEventPage.am);
        } else if(splitTime[1].equalsIgnoreCase("pm")) {
            clickOn(newEventPage.pm);
        }
        clickOn(newEventPage.endDate);
    }

    @Step
    public void tapStartRepeat() {
        clickOn(newEventPage.startRepeat);
    }

    @Step
    public void tapEndRepeat() {
        waitFor(newEventPage.newEventTitle);
        clickOn(newEventPage.endRepeat);
    }

    @Step
    public void tapInvitees() {
        waitFor(newEventPage.newEventTitle);
        clickOn(newEventPage.invitees);
    }

    @Step
    public void tapAdd() {
        clickOn(newEventPage.addButton);
    }
}
