Feature: Adding an Actor

  Scenario: Adding a new actor
    Given the user wants to add a new actor
    When the user submits the actor details
    Then the actor should be added to the database
    And the user should receive a success message
