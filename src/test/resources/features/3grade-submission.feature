Feature: Grade Submission

 Scenario Outline: Submit Grade
    Given The associate is logged in as associate.
    When The associate clicks on the get pending claims button.
    When The associate clicks the select this pending claim button next to the first claim.
    When The associate selects the "<pendingPassingGrade>" passing grade option.
    When The associate selects the "<finalGrade>" final grade option.
    When The associate clicks the submit final grade button.
    Then The associate should see an alert that says "<alert>".

  Examples:
    |pendingPassingGrade|finalGrade|alert        |
    |C                  |A         |Claim Updated|
