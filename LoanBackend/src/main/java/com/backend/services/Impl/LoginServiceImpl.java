package com.backend.services.Impl;

import com.backend.domain.Login;
import com.backend.repositories.LoginRepository;
import com.backend.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by lukekramer on 22/08/2016.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginRepository repository;
    @Override
    public Login create(Login entity) {
        return repository.save(entity);
    }

    @Override
    public Login readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Login> readAll() {
        Set<Login> result = new HashSet<Login>();

        Iterator iterator = repository.findAll().iterator();
        while(iterator.hasNext()){
            result.add((Login) iterator.next());
        }
        /*while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }*/
        return result;
    }

    @Override
    public Login update(Login entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Login entity) {
        repository.delete(entity);
    }
}
