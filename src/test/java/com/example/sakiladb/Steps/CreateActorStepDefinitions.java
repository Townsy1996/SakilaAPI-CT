package com.example.sakiladb.Steps;

import com.example.sakiladb.entities.Actor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class CreateActorStepDefinitions {
    private Actor newActor;
    private ResponseEntity<Actor> responseEntity;
    private Iterable<Actor> actorListBeforeCreation;
    private Iterable<Actor> actorListAfterCreation;

    @Given("I have a new actor with first name {string} and last name {string}")
    public void i_have_a_new_actor_with_first_name_and_last_name(String firstName, String lastName) {
        newActor = new Actor();
        newActor.setFirstName(firstName);
        newActor.setLastName(lastName);
        // You may need to set other properties of the new actor as well
    }

    @When("I send a POST request to the endpoint {string} with the actor details")
    public void i_send_a_post_request_to_the_endpoint_with_the_actor_details(String endpoint) {
        String url = "http://16.171.0.136:8080" + endpoint; // Assuming your API is running locally on port 8080
        RestTemplate restTemplate = new RestTemplate();
        responseEntity = restTemplate.postForEntity(url, newActor, Actor.class);
    }

    @Then("the response should contain the newly created actor")
    public void the_response_should_contain_the_newly_created_actor() {
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        Actor createdActor = responseEntity.getBody();
        assertEquals(newActor.getFirstName(), createdActor.getFirstName());
        assertEquals(newActor.getLastName(), createdActor.getLastName());
        // You may need to compare other properties of the new actor as well
    }

    @Then("the actor list should contain the new actor")
    public void the_actor_list_should_contain_the_new_actor() {
        RestTemplate restTemplate = new RestTemplate();
        actorListBeforeCreation = restTemplate.getForObject("http://16.171.0.136:8080/actor/getAll", Iterable.class);

        // You may need to retrieve the actor list again to check if the new actor has been added
        actorListAfterCreation = restTemplate.getForObject("http://16.171.0.136:8080/actor/getAll", Iterable.class);

        // Assert that the actor list after creation contains one more actor than before

    }
}
