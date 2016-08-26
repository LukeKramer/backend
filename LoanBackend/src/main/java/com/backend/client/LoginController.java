package com.backend.client;

import com.backend.domain.Login;

import com.backend.services.LoginService;
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
public class LoginController {


    // Inject Service
    @Autowired
    private LoginService loginService;

   //-------------------Create a Login--------------------------------------------------------

    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public ResponseEntity<Void> createLogin(@RequestBody Login login, UriComponentsBuilder ucBuilder) {
        loginService.create(login);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/login/{id}").buildAndExpand(login.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single Login--------------------------------------------------------
    @RequestMapping(value = "/login/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Login> getLogin(@PathVariable("id") long id) {
        Login login= loginService.readById(id);
        if (login == null) {
            return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Login>(login, HttpStatus.OK);
    }


    //-------------------Retrieve All Logins--------------------------------------------------------

    @RequestMapping(value = "/logins/", method = RequestMethod.GET)
    public ResponseEntity<Set<Login>> getLogins() {
        Set<Login> login = loginService.readAll();
        if(login.isEmpty()){
            return new ResponseEntity<Set<Login>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Login>>(login, HttpStatus.OK);
    }

    //------------------- Update a Login --------------------------------------------------------

    @RequestMapping(value = "/login/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Login> updateLogin(@PathVariable("id") long id, @RequestBody Login login) {

        Login currentLogin = loginService.readById(id);

        if (currentLogin==null) {
            return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
        }
        Login updatedlogin = new Login.Builder().copy(currentLogin)
                .Password(login.getPassword())
                .Userid(login.getUserid())
                .Username(login.getUsername())
                .build();
        loginService.update(updatedlogin);
        return new ResponseEntity<Login>(updatedlogin, HttpStatus.OK);
    }

    //------------------- Delete a Login --------------------------------------------------------

    @RequestMapping(value = "/login/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Login> deleteLoan(@PathVariable("id") long id) {
        Login login = loginService.readById(id);
        if (login == null) {
            return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
        }
        loginService.delete(login);
        return new ResponseEntity<Login>(HttpStatus.NO_CONTENT);
    }

}