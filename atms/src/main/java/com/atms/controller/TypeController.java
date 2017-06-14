package com.atms.controller;

import com.atms.model.Type;
import com.atms.service.TypeService;
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
public class TypeController {

    private final TypeService typeService;
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @RequestMapping(value = "/api/type", method = RequestMethod.GET)
    public ResponseEntity<List<Type>> getAll() {
        List<Type> types = typeService.findAll();
        if (types == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/type/{typeId}", method = RequestMethod.GET)
    public ResponseEntity<Type> get(@PathVariable("typeId") String typeId) {
        Type type = typeService.findOne(Integer.parseInt(typeId));
        if (type == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/type", method = RequestMethod.POST)
    public ResponseEntity<Type> add(@RequestBody String body) {
        Type type;
        try {
            type = objectMapper.readValue(body, Type.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (typeService.findOne(type.getTypeId()) != null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typeService.save(type), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/type/{typeId}", method = RequestMethod.PUT)
    public ResponseEntity<Type> update(@RequestBody String body,
                                       @PathVariable("typeId") String typeId) {
        Type type;
        try {
            type = objectMapper.readValue(body, Type.class);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Type oldType = typeService.findOne(Integer.parseInt(typeId));
        if (oldType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oldType.setTypeValue(type.getTypeValue());
        oldType.setTasks(type.getTasks());
        oldType = typeService.update(oldType);
        return new ResponseEntity<>(oldType, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/type/{typeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("typeId") String typeId) {
        if (typeService.delete(Integer.parseInt(typeId))) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
