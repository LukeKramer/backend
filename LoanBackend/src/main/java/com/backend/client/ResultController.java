package com.backend.client;

import com.backend.domain.Result;
import com.backend.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

/**
 * Created by lukekramer on 22/08/2016.
 */

@RestController
public class ResultController {

    // Inject Service
    @Autowired
    private ResultService resultService;

    //-------------------Create a Result--------------------------------------------------------

    @RequestMapping(value = "/result/", method = RequestMethod.POST)
    public ResponseEntity<Void> createResult(@RequestBody Result result , UriComponentsBuilder ucBuilder) {
        resultService.create(result);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/result/{id}").buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single Result--------------------------------------------------------
    @RequestMapping(value = "/result/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> getResult(@PathVariable("id") long id) {
        Result result= resultService.readById(id);
        if (result == null) {
            return new ResponseEntity<Result>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Result>(result, HttpStatus.OK);
    }


    //-------------------Retrieve All Results--------------------------------------------------------

    @RequestMapping(value = "/results/", method = RequestMethod.GET)
    public ResponseEntity<Set<Result>> getResults() {
        Set<Result> result = resultService.readAll();
        if(result.isEmpty()){
            return new ResponseEntity<Set<Result>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Result>>(result, HttpStatus.OK);
    }

    //------------------- Update a Result --------------------------------------------------------

    @RequestMapping(value = "/result/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Result> updateResult(@PathVariable("id") long id, @RequestBody Result result) {

        Result currentResult = resultService.readById(id);

        if (currentResult==null) {
            return new ResponseEntity<Result>(HttpStatus.NOT_FOUND);
        }
        Result updatedresult = new Result.Builder().copy(currentResult)
                .ClientID(result.getClientid())
                .LoanID(result.getLoanid())
                .Status(result.getStatus())
                .Date(result.getDate())
                .Build();
        resultService.update(updatedresult);
        return new ResponseEntity<Result>(updatedresult, HttpStatus.OK);
    }

    //------------------- Delete a Result --------------------------------------------------------

    @RequestMapping(value = "/result/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Result> deleteLoan(@PathVariable("id") long id) {
        Result result = resultService.readById(id);
        if (result == null) {
            return new ResponseEntity<Result>(HttpStatus.NOT_FOUND);
        }
        resultService.delete(result);
        return new ResponseEntity<Result>(HttpStatus.NO_CONTENT);
    }

}