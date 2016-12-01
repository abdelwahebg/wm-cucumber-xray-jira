Feature: As a Mobility User
    I can access Availability Mobile Application using The Productivity Application
    So that I can see my OSCA score

  Scenario Outline: Sign In with incorrect credentials on My Productivity app should display error message - @storyKey:AMA-1 @testKey:AMA-5 @executionKey:AMA-2
    Given I am on My Productivity application landing page
    When I sign in with invalid credentials 
      |userId | <invalid_user_id> |
      |password | <password> |
      |domain |Home Office|
      |country |United States|
      |storeNumber| 1021 |
    Then error message is displayed
    And error text contains "Invalid User Id or Password"
    When I confirm the error message
    Then error message disappears

    Examples:
      |invalid_user_id|password||
      |Ramandeep|XYZabd||


  Scenario: Valid login in My Productivity App displays availability in app menu - @storyKey:AMA-1 @testKey:AMA-4 @executionKey:AMA-2
    Given I am on My Productivity application landing page
    When I sign in with valid credentials 
      |userId | vn93605 |
      |password | 55DQicFYx/TXSpoLugCMc+nE7xHqwLZm |
      |domain | Home Office |
      |country | United States |
      |storeNumber| 1021 |
    Then Application Menu is displayed
    And Application Menu has "5" applications
    When I sign out
    Then My Productivity application landing page is displayed
