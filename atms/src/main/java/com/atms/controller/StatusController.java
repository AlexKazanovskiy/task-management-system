package com.atms.controller;

import com.atms.model.Status;
import com.atms.service.StatusService;
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

public class StatusController {

    private final StatusService statusService;
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping(value = "/api/status", method = RequestMethod.GET)
    public ResponseEntity<List<Status>> getAll() {
        List<Status> statuses = statusService.findAll();
        if (statuses == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/status/{statusId}", method = RequestMethod.GET)
    public ResponseEntity<Status> get(@PathVariable("statusId") String statusId) {
        Status status = statusService.findOne(Integer.parseInt(statusId));
        if (status == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/status", method = RequestMethod.POST)
    public ResponseEntity<Status> add(@RequestBody String body) {
        Status status;
        try {
            status = objectMapper.readValue(body, Status.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (statusService.findOne(status.getStatusId()) != null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statusService.save(status), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/status/{statusId}", method = RequestMethod.PUT)
    public ResponseEntity<Status> update(@RequestBody String body,
                                         @PathVariable("statusId") String statusId) {
        Status status;
        try {
            status = objectMapper.readValue(body, Status.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Status oldStatus = statusService.findOne(Integer.parseInt(statusId));
        if (oldStatus == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oldStatus.setValue(status.getValue());
        oldStatus.setTasks(status.getTasks());
        oldStatus = statusService.update(oldStatus);
        return new ResponseEntity<>(oldStatus, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/status/{statusId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("statusId") String statusId) {
        if (statusService.delete(Integer.parseInt(statusId))) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
