package com.backend.repositories;

import com.backend.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lukekramer on 14/08/2016.
 */

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}