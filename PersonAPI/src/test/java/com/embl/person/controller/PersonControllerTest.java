package com.embl.person.controller;

import org.junit.Test;

import com.jayway.restassured.RestAssured;


public class PersonControllerTest {
	@Test
	public void test_APIWithBasicAuthentication_ShouldBeGivenAccess() {
		RestAssured.given().
	        auth().
	        preemptive().
	        basic("admin", "password").
	    when().
	        get("/api/v1/persons/").
	    then().
	        assertThat().
	        statusCode(200);
	}
}
