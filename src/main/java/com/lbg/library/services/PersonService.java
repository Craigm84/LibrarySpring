package com.lbg.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbg.library.domain.Person;
import com.lbg.library.repo.PersonRepo;

@Service
public class PersonService {

	private PersonRepo repo;

	public PersonService(PersonRepo repo) {
		super();
		this.repo = repo;
	}

	public ResponseEntity<Person> createPerson(Person newPerson) {
		Person created = this.repo.save(newPerson);
		return new ResponseEntity<Person>(created, HttpStatus.CREATED);
	}

	public List<Person> getPerson() {
		return this.repo.findAll();
	}

	public ResponseEntity<Person> getPerson(int id) {
		Optional<Person> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		Person body = found.get();
		return ResponseEntity.ok(body);
	}

	public Boolean deletePerson(int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public ResponseEntity<Person> updatePerson(int id, Person newPerson) {
		Optional<Person> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		Person existing = found.get();

		if (newPerson.getFirstName() != null) {
			existing.setFirstName(newPerson.getFirstName());
		}

		if (newPerson.getLastName() != null) {
			existing.setLastName(newPerson.getLastName());
		}

		Person updated = this.repo.save(existing);

		return ResponseEntity.ok(updated);
	}
}
