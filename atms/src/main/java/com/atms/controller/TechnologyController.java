package com.atms.controller;

import com.atms.model.Technology;
import com.atms.service.TechnologyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */

@RestController
@CrossOrigin
public class TechnologyController {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private final TechnologyService technologyService;

    @Autowired
    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @RequestMapping(value = "/api/technology", method = RequestMethod.GET)
    public ResponseEntity<List<Technology>> getAll() {
        List<Technology> technologies = technologyService.findAll();
        if (technologies == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(technologies, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/technology/{technologyId}", method = RequestMethod.GET)
    public ResponseEntity<Technology> get(@PathVariable("technologyId") Technology technology) {
        return new ResponseEntity<>(technology, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/technology", method = RequestMethod.POST)
    public ResponseEntity<Technology> add(@RequestBody String body) {
        Technology technology;
        try {
            technology = objectMapper.readValue(body, Technology.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (technologyService.findOne(technology.getTechnologyId()) != null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(technologyService.save(technology), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/technology/{technologyId}", method = RequestMethod.PUT)
    public ResponseEntity<Technology> update(@RequestBody String body,
                                             @PathVariable("technologyId") Technology oldTechnology) {
        Technology technology;
        try {
            technology = objectMapper.readValue(body, Technology.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        oldTechnology.setDescription(technology.getDescription());
        oldTechnology.setTitle(technology.getTitle());
        oldTechnology = technologyService.update(oldTechnology);
        return new ResponseEntity<>(oldTechnology, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/technology/{technologyId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("technologyId") String technologyId) {
        if (technologyService.delete(Integer.parseInt(technologyId))) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
