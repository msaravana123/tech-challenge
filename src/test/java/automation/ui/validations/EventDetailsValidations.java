package automation.ui.validations;

import automation.ui.pageobjects.EventDetailsPage;
import io.appium.java_client.MobileBy;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

public class EventDetailsValidations extends UIInteractionSteps {

    EventDetailsPage eventDetailsPage;

    @Step
    public String getPageTitle() {
        return eventDetailsPage.eventDetailsTitle.getAttribute("name");
    }

    @Step
    public String getMeetingTitle(String title) {
        return getDriver().findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + title + "']")).getAttribute("name");
    }

    @Step
    public String getInviteesDetails() {
        return eventDetailsPage.invitees.getAttribute("name");
    }

    @Step
    public String getMeetingDetails(String title) {
        return getDriver().findElement(MobileBy.AccessibilityId(title)).getAttribute("name");
    }
}
