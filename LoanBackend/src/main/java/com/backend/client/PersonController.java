package com.backend.client;

import com.backend.domain.Person;
import com.backend.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

/**
 * Created by lukekramer on 14/08/2016.
 */
@RestController
public class PersonController {

    // Inject Service
    @Autowired
    private PersonService personService;

    //-------------------Create a Person--------------------------------------------------------

    @RequestMapping(value = "/person/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPerson(@RequestBody Person person, UriComponentsBuilder ucBuilder) {
        personService.create(person);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/person/{id}").buildAndExpand(person.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single Person--------------------------------------------------------
    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable("id") long id) {
        Person person = personService.readById(id);
        if (person == null) {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }


    //-------------------Retrieve All People--------------------------------------------------------

    @RequestMapping(value = "/people/", method = RequestMethod.GET)
    public ResponseEntity<Set<Person>> getPeople() {
        Set<Person> people = personService.readAll();
        if(people.isEmpty()){
            return new ResponseEntity<Set<Person>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Person>>(people, HttpStatus.OK);
    }

    //------------------- Update a Person --------------------------------------------------------

    @RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {

        Person currentPerson = personService.readById(id);

        if (currentPerson==null) {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        Person updatedPerson = new Person.Builder().copy(currentPerson)
                .FirstName(person.getFirstName())
                .LastName(person.getLastName())
                .PhoneNumber(person.getPhoneNumber())
                .Email(person.getEmailAddress())
                .Income(person.getIncome())
                .build();
        personService.update(updatedPerson);
        return new ResponseEntity<Person>(updatedPerson, HttpStatus.OK);
    }

    //------------------- Delete a Story --------------------------------------------------------

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deletePerson(@PathVariable("id") long id) {
        Person person = personService.readById(id);
        if (person == null) {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        personService.delete(person);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }

}

