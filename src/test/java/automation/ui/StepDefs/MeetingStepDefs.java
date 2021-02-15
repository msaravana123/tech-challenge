package automation.ui.StepDefs;

import automation.ui.actions.*;
import automation.ui.pageobjects.RepeatPage;
import automation.ui.validations.EventDetailsValidations;
import automation.utils.DateHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MeetingStepDefs {

    @Steps
    CalendarHomeActions calendarHomeActions;

    @Steps
    NewEventPageActions newEventPageActions;

    @Steps
    RepeatActions repeatActions;

    @Steps
    EndRepeatActions endRepeatActions;

    @Steps
    AddInviteesActions addInviteesActions;

    @Steps
    InviteesActions inviteesActions;

    @Steps
    EventDetailsActions eventDetailsActions;

    @Steps
    EventDetailsValidations eventDetailsValidations;

    @Given("I have launched the Calendar App")
    public void launchCalendarApp() {
        calendarHomeActions.launchCalendatApp();
        calendarHomeActions.tapAddButton();
    }

    @When("It is a working {string}")
    public void setDayForMeeting(String day) {
        LocalDate getDate = DateHelper.getNextDay(LocalDate.now(), day.toUpperCase());
        Serenity.setSessionVariable("startDate").to(getDate);
    }

    @And("I want to book a meeting with the title {string}")
    public void setMeetingTitle(String title) {
        Serenity.setSessionVariable("title").to(title);
        newEventPageActions.setMeetingTitle(title);
    }

    @And("Meeting is between {string} to {string}")
    public void setMeetingTime(String start, String end) {
        Serenity.setSessionVariable("startTime").to(start);
        Serenity.setSessionVariable("endTime").to(end);
        newEventPageActions.setStartTime(Serenity.sessionVariableCalled("startDate"), start);
        newEventPageActions.endStartTime(end);
    }

    @And("Meeting is recurring for next {int} months")
    public void setRepeats(int months) {
        newEventPageActions.tapStartRepeat();
        repeatActions.tapOnMonths();
        newEventPageActions.tapEndRepeat();
        endRepeatActions.setEndDate(months - 1, Serenity.sessionVariableCalled("startDate"));
    }

    @And("I invite {string} number of people")
    public void setInvitees(String invitees) {
        Serenity.setSessionVariable("invitees").to(invitees);
        newEventPageActions.tapInvitees();
        addInviteesActions.setInvitees(invitees);
        inviteesActions.goBackToEvent();
    }

    @And("I save the meeting")
    public void saveMeeting() {
        newEventPageActions.tapAdd();
    }


    @Then("I Check if the meeting is created as expected")
    public void verifyMeeting() {
        String title = Serenity.sessionVariableCalled("title");
        String startTime = Serenity.sessionVariableCalled("startTime");
        String endTime = Serenity.sessionVariableCalled("endTime");
        LocalDate startDate = Serenity.sessionVariableCalled("startDate");
        String timeLabel = "from " + startTime + " to " + endTime;
        String meetingLabel = title + ", " + timeLabel;

        calendarHomeActions.tapOnEvent(meetingLabel);
        eventDetailsActions.waitForEventDetails();

        assertThat("Meeting Title",
                eventDetailsValidations.getMeetingTitle(title),
                is(equalTo(title)));

        assertThat("Time of Meeting",
                eventDetailsValidations.getMeetingDetails(timeLabel),
                is(equalTo(timeLabel)));

        int nInvitees = (int) Serenity.sessionVariableCalled("invitees")
                .toString().chars().filter(ch -> ch == ',').count() + 1;

        assertThat("Number of invitees",
                eventDetailsValidations.getInviteesDetails(),
                is(equalTo("Invitees, " + nInvitees)));

    }
}
