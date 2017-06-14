package com.atms.controller;

import com.atms.model.Developer;
import com.atms.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Alex Kazanovskiy.
 */

@RestController
public class LoginController {

    private final DeveloperService developerService;

    @Autowired
    public LoginController(DeveloperService developerService) {
        this.developerService = developerService;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<Developer> registration(@Valid Developer developer) {
        Developer newDeveloper;
        try {
            newDeveloper = developerService.save(developer);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newDeveloper, HttpStatus.OK);
    }
}
