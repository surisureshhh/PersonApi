package com.embl.person.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.embl.person.exception.ResourceNotFoundException;
import com.embl.person.model.Person;
import com.embl.person.repository.PersonRepository;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;

	public List<Person> savePersons(List<Person> PersonsList) {
		List<Person> personResponse = (List<Person>) personRepository.saveAll(PersonsList);
		return personResponse;
	}

	public List<Person> getAllPersons() {
		List<Person> personResponse = (List<Person>) personRepository.findAll();
		return personResponse;
	}

	public Person getPersonById(long personId) throws ResourceNotFoundException {
		Optional<Person> personList = personRepository.findById(personId);
		if (Optional.empty().equals(personList)) {
			throw new ResourceNotFoundException("No Records found with id : " + personId);
		}
		return personList.get();
	}

	public void updatePerson(Person personsList) throws ResourceNotFoundException {
		Optional<Person> personDb = personRepository.findById(personsList.getId());
		if (personDb.isPresent()) {
			Person personUpdate = personDb.get();
			personUpdate.setId(personsList.getId());
			personUpdate.setFirst_name(personsList.getFirst_name());
			personUpdate.setLast_name(personsList.getLast_name());
			personUpdate.setAge(personsList.getAge());
			personUpdate.setFavourite_colour(personsList.getFavourite_colour());
			personUpdate.setHobby(personsList.getHobby());
			personRepository.save(personUpdate);
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + personsList.getId());
		}
	}

	public void deletePersonById(long personId) throws ResourceNotFoundException {
		try {
			personRepository.deleteById(personId);
		} catch (Exception e) {
			throw new ResourceNotFoundException("No Records found in DB with id : " + personId);
		}
	}

	public void deleteAll() {
		personRepository.deleteAll();
	}
}
