package com.backend.services.Impl;

import com.backend.domain.Loan;
import com.backend.domain.Result;
import com.backend.repositories.ResultRepository;
import com.backend.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by lukekramer on 22/08/2016.
 */
@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    ResultRepository repository;
    @Override
    public Result create(Result entity) {
        return repository.save(entity);
    }

    @Override
    public Result readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Result> readAll() {
        Set<Result> result = new HashSet<Result>();

        Iterator iterator = repository.findAll().iterator();
        while(iterator.hasNext()){
            result.add((Result) iterator.next());
        }

       /* while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }*/
        return result;
    }

    @Override
    public Result update(Result entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Result entity) {
        repository.delete(entity);
    }
}
