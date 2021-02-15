package automation.ui.actions;

import automation.ui.pageobjects.EventDetailsPage;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

public class EventDetailsActions extends UIInteractionSteps {

    EventDetailsPage eventDetailsPage;

    @Step
    public void waitForEventDetails() {
        waitFor(eventDetailsPage.eventDetailsTitle);
    }

}
