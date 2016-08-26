package com.backend.repositories;

import com.backend.domain.Login;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lukekramer on 16/08/2016.
 */
public interface LoginRepository extends CrudRepository<Login,Long> {
}
