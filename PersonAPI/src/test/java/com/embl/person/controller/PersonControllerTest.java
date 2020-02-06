package com.embl.person.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;

@RunWith(SpringRunner.class)
public class PersonControllerTest {
	@Test
	public void test_APIWithBasicAuthentication_ShouldBeGivenAccess() {
		RestAssured.given().auth().preemptive().basic("admin", "password").when().get("/api/v1/persons").then()
				.assertThat().statusCode(200);
	}

	@Test
	public void testSavePerson() {
		String jsonString = "{\r\n" + "        \"id\": 1,\r\n" + "        \"first_name\": \"suresh\",\r\n"
				+ "        \"last_name\": \"pasupuleti\",\r\n" + "        \"age\": \"34\",\r\n"
				+ "        \"favourite_colour\": \"blue\",\r\n" + "        \"hobby\": [\r\n"
				+ "            \"cricket,tennis\"\r\n" + "        ]\r\n" + "    }";
		
		RestAssured.given().body(jsonString).with().contentType("application/json").auth().preemptive().basic("admin", "password").then().expect().
        statusCode(200).when().post("/api/v1/persons");
	}
	@Test
	public void testGetPersons() {
		
		RestAssured.given().auth().preemptive().basic("admin", "password").then().expect().
        statusCode(200).when().get("/api/v1/persons");
	}
}
