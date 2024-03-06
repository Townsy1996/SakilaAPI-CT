Feature: Create Actor
  As a user
  I want to create a new actor
  So that I can add them to the database

  Scenario: Create a new actor
    Given I have a new actor with first name "Tom" and last name "Hanks"
    When I send a POST request to the endpoint "/actor/create" with the actor details
    Then the response should contain the newly created actor
    And the actor list should contain the new actor
