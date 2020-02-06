package com.embl.person.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.embl.person.exception.ResourceNotFoundException;
import com.embl.person.model.Person;
import com.embl.person.service.PersonService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/v1")
public class PersonController {
	@Autowired
	private PersonService personService;
	ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * fetch all the records from person table
	 * @return
	 * @throws JsonProcessingException
	 */
	@GetMapping("/persons")
	@ResponseBody
	public ResponseEntity<List<Person>> getAllPersons() throws JsonProcessingException {
		List<Person> personResponse = (List<Person>) personService.getAllPersons();
		return ResponseEntity.ok().body(personResponse);
	}

	/**
	 * this method creates the entry in Person table
	 * @param personString
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@PostMapping("/persons")
	@ResponseBody
	public ResponseEntity<List<Person>> savePerson(@RequestBody String personString)
			throws JsonParseException, JsonMappingException, IOException {
		List<Person> personsList = new ArrayList<Person>();
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			JsonNode rootNode = objectMapper.readTree(personString);
			if (rootNode.has("person") || rootNode.has("Person")) {
				String rootString = rootNode.get("person").toString();
				personsList = Arrays.asList(objectMapper.readValue(rootString, Person[].class));
			} else {
				Person personDetails = objectMapper.readValue(personString, Person.class);
				personsList.add(personDetails);
			}
		List<Person> personResponse = (List<Person>) personService.savePersons(personsList);
		return ResponseEntity.ok().body(personResponse);
	}

	/**
	 * Fetch person details based on ID
	 * @param personId
	 * @return
	 * @throws JsonProcessingException
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/persons/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId)
			throws JsonProcessingException, ResourceNotFoundException {
		Person person = personService.getPersonById(personId);
		return ResponseEntity.ok().body(person);
	}

	/**
	 * Updates person record based the ID
	 * @param personId
	 * @param personJson
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/persons/{id}")
	public Map<String, Boolean> updatePerson(@PathVariable(value = "id") Long personId,
			@RequestBody String personJson)
			throws JsonMappingException, JsonProcessingException, ResourceNotFoundException {
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Person personDetails = objectMapper.readValue(personJson, Person.class);
		personDetails.setId(personId);
		personService.updatePerson(personDetails);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Record is Updated for id:"+personId, Boolean.TRUE);
		return response;
	}

	/**
	 * This method delete the record based on Person ID
	 * @param personId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/persons/{id}")
	public Map<String, Boolean> deletePersonById(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		personService.deletePersonById(personId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/**
	 * This method delete all records from Person Table
	 * @return
	 */
	@DeleteMapping("/persons")
	public Map<String, Boolean> deleteAllPersons() {
		personService.deleteAll();
		Map<String, Boolean> response = new HashMap<>();
		response.put("All records are deleted", Boolean.TRUE);
		return response;
	}
}
