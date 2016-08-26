package com.backend.services.Impl;

import com.backend.domain.Loan;
import com.backend.repositories.LoanRepository;
import com.backend.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by lukekramer on 16/08/2016.
 */
@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    LoanRepository repository;


    @Override
    public Loan create(Loan entity) {
        return repository.save(entity);
    }

    @Override
    public Loan readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Loan> readAll() {
        Set<Loan> result = new HashSet<Loan>();

        Iterator iterator = repository.findAll().iterator();
        while(iterator.hasNext()){
            result.add((Loan) iterator.next());
        }

        /*while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }*/
        return result;
    }
    @Override
    public Loan update(Loan entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(Loan entity) {
        repository.delete(entity);

    }
}


