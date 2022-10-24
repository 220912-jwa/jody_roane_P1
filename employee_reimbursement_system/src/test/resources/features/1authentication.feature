Feature: Authentication

  Scenario Outline: Login Negative
    Given The employee is on the login page.
    When The employee types incorrect "<username>" into the username input.
    When The employee types incorrect "<password>" into the password input.
    When the employee clicks the login button a.
    Then the employee sees an alert that that says "<alert>".

  Examples:
    |username |password|alert                 |
    |employee1|yochill1|Authentication failed.|
    |employee2|yochill2|Authentication failed.|

  Scenario Outline: Login Positive
    Given The employee is on the login page.
    When The employee types correct "<username>" into the username input.
    When The employee types correct "<password>" into the password input.
    When The employee clicks the login button b.
    Then The employee should be on the "<title>" page.
    Then The employee should see their "<fname>","<lname>","<role>" on the home page.

  Examples:
    |username |password |title|fname|lname    |role     |
    |employee1|password1|Home |test |developer|associate|
    |employee2|password2|Home |test |finance  |manager  |

  Scenario Outline: Navigation
    Given The manager is logged in as manager.
    When The manager clicks on "<homeLink>" link.
    Then The manager should be on the "<title1>" page a.
    When The manager clicks on the "<logoutLink>" link.
    Then the manager should be on the "<title2>" page b.

  Examples:
    |homeLink|title1|logoutLink|title2                       |
    |Home    |Home  |Logout    |Employee Reimbursement Portal|