package com.atms.controller;

import com.atms.model.DevType;
import com.atms.service.DevTypeService;
import org.codehaus.jackson.map.ObjectMapper;
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
public class DevTypeController {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private final DevTypeService devTypeService;

    @Autowired
    public DevTypeController(DevTypeService devTypeService) {
        this.devTypeService = devTypeService;
    }

    @RequestMapping(value = "/api/devType", method = RequestMethod.GET)
    public ResponseEntity<List<DevType>> getAll() {
        List<DevType> devTypes = devTypeService.findAll();
        if (devTypes == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(devTypes, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/devType/{devTypeId}", method = RequestMethod.GET)
    public ResponseEntity<DevType> getDevType(@PathVariable("devTypeId") DevType devType) {
        return new ResponseEntity<>(devType, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/devType", method = RequestMethod.POST)
    public ResponseEntity<DevType> addDevType(@RequestBody String body) {
        DevType devType = null;
        try {
            devType = objectMapper.readValue(body, DevType.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(devType, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/devType/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DevType> update(@PathVariable("id") DevType oldDevType, @RequestBody String body) {
        DevType devType;
        try {
            devType = objectMapper.readValue(body, DevType.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        oldDevType.setValue(devType.getValue());
        devTypeService.update(oldDevType);
        return new ResponseEntity<>(oldDevType, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/devType/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (devTypeService.delete(Integer.parseInt(id))) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
