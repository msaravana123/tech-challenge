Feature: Create a recurring meeting using Native iOS Calendar App.

  @ui-test
  Scenario Outline: Create a workshop meeting event from 9:30am to 1:30pm every Friday of the week for next 3 months.
  Call a stand up to discuss the progress on Monday for 15 minutes.
    Given I have launched the Calendar App
    When It is a working "<Day>"
    And I want to book a meeting with the title "<Title>"
    And Meeting is between "<From>" to "<To>"
    And Meeting is recurring for next <Months> months
    And I invite "<Invitees>" number of people
    And I save the meeting
    Then I Check if the meeting is created as expected

    Examples:
      | Day    | Title    | From    | To      | Months | Invitees                                        |
      | Friday | Workshop | 9:30 am | 1:30 pm | 3      | test1@gmail.com,test2@gmail.com                 |
      | Monday | Stand Up | 9:00 am | 9:15 am | 3      | test1@gmail.com,test2@gmail.com,test3@gmail.com |
