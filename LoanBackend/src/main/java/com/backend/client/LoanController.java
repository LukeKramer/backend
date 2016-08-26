package com.backend.client;

import com.backend.domain.Loan;
import com.backend.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

/**
 * Created by lukekramer on 16/08/2016.
 */
@RestController
public class LoanController {


    // Inject Service
    @Autowired
    private LoanService loanService;

    //-------------------Create a Loan--------------------------------------------------------

    @RequestMapping(value = "/loan/", method = RequestMethod.POST)
    public ResponseEntity<Void> createLoan(@RequestBody Loan loan, UriComponentsBuilder ucBuilder) {
        loanService.create(loan);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/loan/{id}").buildAndExpand(loan.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single Loan--------------------------------------------------------
    @RequestMapping(value = "/loan/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Loan> getLoan(@PathVariable("id") long id) {
        Loan loan = loanService.readById(id);
        if (loan == null) {
            return new ResponseEntity<Loan>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Loan>(loan, HttpStatus.OK);
    }


    //-------------------Retrieve All Loans--------------------------------------------------------

    @RequestMapping(value = "/loans/", method = RequestMethod.GET)
    public ResponseEntity<Set<Loan>> getLoans()throws Exception {
        Set<Loan> loan = loanService.readAll();
        if(loan.isEmpty()){
            return new ResponseEntity<Set<Loan>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Loan>>(loan, HttpStatus.OK);
    }

    //------------------- Update a Loan --------------------------------------------------------

    @RequestMapping(value = "/loans/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Loan> updateLoan(@PathVariable("id") long id, @RequestBody Loan loan) {

        Loan currentLoan = loanService.readById(id);

        if (currentLoan==null) {
            return new ResponseEntity<Loan>(HttpStatus.NOT_FOUND);
        }
        Loan updatedloan = new Loan.Builder().copy(currentLoan)
                .maxLoanAmount(loan.getMaxAmount())
                .minLoanAmount(loan.getMinAmount())
                .build();
        loanService.update(updatedloan);
        return new ResponseEntity<Loan>(updatedloan, HttpStatus.OK);
    }

    //------------------- Delete a Loan --------------------------------------------------------

    @RequestMapping(value = "/loan/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Loan> deleteLoan(@PathVariable("id") long id) {
        Loan loan = loanService.readById(id);
        if (loan == null) {
            return new ResponseEntity<Loan>(HttpStatus.NOT_FOUND);
        }
        loanService.delete(loan);
        return new ResponseEntity<Loan>(HttpStatus.NO_CONTENT);
    }

}


