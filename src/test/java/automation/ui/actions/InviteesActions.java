package automation.ui.actions;

import automation.ui.pageobjects.InviteesPage;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

public class InviteesActions extends UIInteractionSteps {

    InviteesPage inviteesPage;

    @Step
    public void goBackToEvent() {
        waitFor(inviteesPage.inviteesTitle);
        clickOn(inviteesPage.newEventButton);
    }
}
