package com.example.sakiladb.Steps;
import com.example.sakiladb.entities.Actor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddActorSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    private ResponseEntity<String> responseEntity;

    private Actor actorToAdd;

    @Given("the user wants to add a new actor")
    public void givenUserWantsToAddNewActor() {
        actorToAdd = new Actor(0, "John", "Doe");
    }

    @When("the user submits the actor details")
    public void whenUserSubmitsActorDetails() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(objectMapper.writeValueAsString(actorToAdd), headers);
        String baseUrl = "http://localhost:" + port + "/actor/create";
        responseEntity = restTemplate.postForEntity(baseUrl, requestEntity, String.class);
    }

    @Then("the actor should be added to the database")
    public void thenActorShouldBeAddedToDatabase() {
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Then("the user should receive a success message")
    public void thenUserShouldReceiveSuccessMessage() {
        // Additional assertions for success message if applicable
    }
}
