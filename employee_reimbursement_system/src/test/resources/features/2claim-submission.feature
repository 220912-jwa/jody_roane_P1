Feature: Claim Submission

  Scenario Outline: Create Claim
    Given The associate is on the Home page.
    When The associate clicks the get reimbursement form button.
    When The associate selects "<event>" event option.
    When The associate types "<description>" into description field.
    When The associate types "<location>" into the location field.
    When The associate types "<cost>" into the cost field.
    When The associate selects today's date.
    When The associate selects next fridays date.
    When The associate clicks calculate reimbursement.
    Then The associate the value for reimbursement will update.
    When The associate clicks the submit reimbursement page.
    Then The associate should see an alert that says "<confirm>" a.
    When The employee accepts the confirm.
    Then The employee should see an alert that says "<alert>" b.

  Examples:
    |event|description|location|cost|confirm|alert|
    |Other|Private swimming classes.|Palmdale,CA|100.00|Are you sure?|DataBase Update!|
