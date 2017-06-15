package com.atms.controller;

import com.atms.model.Sprint;
import com.atms.service.SprintService;
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
public class SprintController {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private final SprintService sprintService;

    @Autowired
    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @RequestMapping(value = "/api/sprint", method = RequestMethod.GET)
    public ResponseEntity<List<Sprint>> getAll() {
        List<Sprint> sprints = sprintService.findAll();
        if (sprints == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/sprint/{sprintId}", method = RequestMethod.GET)
    public ResponseEntity<Sprint> get(@PathVariable("sprintId") Sprint sprint) {
        return new ResponseEntity<>(sprint, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/sprint", method = RequestMethod.POST)
    public ResponseEntity<Sprint> add(@RequestBody String body) {
        Sprint sprint;
        try {
            sprint = objectMapper.readValue(body, Sprint.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (sprintService.findOne(sprint.getSprintId()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(sprintService.save(sprint), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/sprint{sprintId}", method = RequestMethod.PUT)
    public ResponseEntity<Sprint> update(@RequestBody String body, @PathVariable("sprintId") Sprint oldSprint) {
        Sprint sprint;
        try {
            sprint = objectMapper.readValue(body, Sprint.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        oldSprint.setDateEnd(sprint.getDateEnd());
        oldSprint.setDateStart(sprint.getDateStart());
        oldSprint.setProject(sprint.getProject());
        oldSprint.setTasks(sprint.getTasks());
        return new ResponseEntity<>(sprintService.update(sprint), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/sprint/{sprintId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("sprintId") String sprintId) {
        if (sprintService.delete(Integer.parseInt(sprintId))) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
