package com.thoughtworks.contract;

import com.jayway.restassured.RestAssured;
import com.thoughtworks.Application;
import com.thoughtworks.dto.PetDTO;
import com.thoughtworks.model.Gender;
import com.thoughtworks.model.Pet;
import com.thoughtworks.repository.PetRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class PetsTest {

    @Autowired
    PetRepository repository;

    Pet mickey;
    Pet minnie;
    Pet pluto;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        mickey = new Pet("Mickey Mouse", Gender.MALE, "cat");
        minnie = new Pet("Minnie Mouse", Gender.FEMALE, "dog");
        pluto = new Pet("Pluto", Gender.MALE, "bird");

        repository.deleteAll();
        repository.save(Arrays.asList(mickey, minnie, pluto));

        RestAssured.port = port;
    }

    @Test
    public void canCreate() {
        PetDTO petDTO = new PetDTO("Obama", "male", "animal");
        given().
                header("Content-Type", "application/json").
                body(petDTO).
                when().
                post("/api/pets").
                then().
                statusCode(HttpStatus.OK.value()).
                body("name", Matchers.is("Obama")).
                body("gender", Matchers.is("male")).
                body("type", Matchers.is("animal"));
    }

    @Test
    public void canFetchMickey() {
        int id = mickey.id;
        when().
                get("/api/pets/{id}", id).
                then().
                statusCode(HttpStatus.OK.value()).
                body("name", Matchers.is("Mickey Mouse")).
                body("id", Matchers.is(id));
    }

    @Test
    public void canFetchAll() {
        when().
                get("/api/pets").
                then().
                statusCode(HttpStatus.OK.value()).
                body("name", Matchers.hasItems("Mickey Mouse", "Minnie Mouse", "Pluto")).
                body("gender", Matchers.hasItems("male", "female", "male")).
                body("type", Matchers.hasItems("cat", "dog", "bird"));
    }

    @Test
    public void canDeletePluto() {
        int plutoId = pluto.id;

        when()
                .delete("/api/pets/{id}", plutoId).
                then().
                statusCode(HttpStatus.NO_CONTENT.value());
    }

}