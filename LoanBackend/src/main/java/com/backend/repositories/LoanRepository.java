package com.backend.repositories;

import com.backend.domain.Loan;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lukekramer on 16/08/2016.
 */
public interface LoanRepository extends CrudRepository<Loan,Long> {
}
