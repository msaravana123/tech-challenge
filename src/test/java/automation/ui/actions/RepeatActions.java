package automation.ui.actions;

import automation.ui.pageobjects.RepeatPage;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

public class RepeatActions extends UIInteractionSteps {

    RepeatPage repeatPage;

    @Step
    public void tapOnMonths() {
        waitFor(repeatPage.everyWeek);
        clickOn(repeatPage.everyWeek);
    }
}
