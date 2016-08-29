package com.backend.repositories;

import com.backend.domain.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lukekramer on 16/08/2016.
 */
@Repository
public interface ResultRepository extends CrudRepository<Result,Long> {
}
