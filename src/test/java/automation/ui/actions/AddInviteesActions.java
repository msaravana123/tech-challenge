package automation.ui.actions;

import automation.ui.pageobjects.AddInviteesPage;
import automation.ui.pageobjects.InviteesPage;
import automation.ui.pageobjects.RepeatPage;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

public class AddInviteesActions extends UIInteractionSteps {

    AddInviteesPage addInviteesPage;

    @Step
    public void setInvitees(String invitees) {
        waitFor(addInviteesPage.addInviteesTitle);
        $(addInviteesPage.toField).sendKeys(invitees);
        clickOn(addInviteesPage.doneButton);
    }
}
