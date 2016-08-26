package com.backend.services.Impl;

import com.backend.domain.Person;
import com.backend.repositories.PersonRepository;
import com.backend.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by lukekramer on 14/08/2016.
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository repository;


    @Override
    public Person create(Person entity) {
        return repository.save(entity);
    }

    @Override
    public Person readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Person> readAll() {
        Set<Person> result = new HashSet<Person>();

        Iterator iterator = repository.findAll().iterator();
        while(iterator.hasNext()){
            result.add((Person) iterator.next());
        }


        /*while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }*/
        return result;
    }
    @Override
    public Person update(Person entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(Person entity) {
        repository.delete(entity);

    }
}

