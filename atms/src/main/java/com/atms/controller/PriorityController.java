package com.atms.controller;

import com.atms.model.Priority;
import com.atms.service.PriorityService;
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
public class PriorityController {

    private final PriorityService priorityService;
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @RequestMapping(value = "/api/priority", method = RequestMethod.GET)
    public ResponseEntity<List<Priority>> getAll() {
        List<Priority> priorities = priorityService.findAll();
        if (priorities == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(priorities, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/priority/{priorityId}", method = RequestMethod.GET)
    public ResponseEntity<Priority> get(@PathVariable("priorityId") String priorityId) {
        Priority priority = priorityService.findOne(Integer.parseInt(priorityId));
        if (priority == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(priority, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/priority", method = RequestMethod.POST)
    public ResponseEntity<Priority> add(@RequestBody String body) {
        Priority priority;
        try {
            priority = objectMapper.readValue(body, Priority.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (priorityService.findOne(priority.getPriorityId()) != null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(priorityService.save(priority), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/priority/{priorityId}", method = RequestMethod.PUT)
    public ResponseEntity<Priority> update(@RequestBody String body,
                                           @PathVariable("priorityId") String priorityId) {
        Priority priority;
        try {
            priority = objectMapper.readValue(body, Priority.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Priority oldPriority = priorityService.findOne(Integer.parseInt(priorityId));
        if (oldPriority == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oldPriority.setPriorityValue(priority.getPriorityValue());
        oldPriority.setTasks(priority.getTasks());
        oldPriority = priorityService.update(oldPriority);
        return new ResponseEntity<>(oldPriority, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/priority/{priorityId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("priorityId") String priorityId) {
        if (priorityService.delete(Integer.parseInt(priorityId))) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
