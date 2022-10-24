Feature: Approve Claim
  Scenario Outline: Approve Claim
    Given The manager is logged in as manager.
    When The manager clicks the get claims seeking approval button.
    When The manager clicks the select this claim button.
    When The manager types "<adjustment>" into the reimbursement adjustment field.
    When The manager types "<reason>" into the adjustment reason field.
    When The manager click the submit adjustment button.
    Then The manager should see the reimbursement update.
    When The manager selects the "<claimStatus>" claim status option.
    When the manager checks the checkbox for award status.
    Then The manager should see an alert that says "<alert>" a.

  Examples:
    |adjustment|reason|claimStatus|alert|
    |100.00    |Swimming is worth it.|Awarded|Claim Updated|
