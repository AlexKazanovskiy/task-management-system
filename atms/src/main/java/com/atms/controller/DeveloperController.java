package com.atms.controller;

import com.atms.model.Developer;
import com.atms.model.PasswordResetToken;
import com.atms.model.Project;
import com.atms.notify.Notifier;
import com.atms.service.DeveloperService;
import com.atms.service.ProjectService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Alex Kazanovskiy.
 */

@RestController
public class DeveloperController {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private final DeveloperService developerService;
    private final ProjectService projectService;
    private final Notifier notifier;

    @Autowired
    public DeveloperController(DeveloperService developerService, ProjectService projectService, Notifier notifier) {
        this.developerService = developerService;
        this.projectService = projectService;
        this.notifier = notifier;
    }

    @RequestMapping(value = "/api/developer", method = RequestMethod.GET)
    public ResponseEntity<List<Developer>> getAll() {
        List<Developer> developers = developerService.findAll();
        if (developers == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(developers, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/developer/project/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<List<Developer>> getByProject(@PathVariable("projectId") Project project) {
        List<Developer> developers = developerService.findByProject(project);
        if (developers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(developers, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/developer/{developerId}", method = RequestMethod.GET)
    public ResponseEntity<Developer> getDeveloper(@PathVariable("developerId") Developer developer) {
        return new ResponseEntity<>(developer, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/developer", method = RequestMethod.POST)
    public ResponseEntity<Developer> add(@RequestBody String body) {
        Developer developer = null;
        try {
            developer = objectMapper.readValue(body, Developer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (developerService.findOne(developer.getDeveloperId()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        developer = developerService.save(developer);
        return new ResponseEntity<>(developer, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/developer/{developerId}", method = RequestMethod.PUT)
    public ResponseEntity<Developer> update(@PathVariable("developerId") Developer oldDeveloper, @RequestBody String body) {
        Developer developer = null;
        try {
            developer = objectMapper.readValue(body, Developer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        oldDeveloper.setTelephone(developer.getTelephone());
        oldDeveloper.setLastName(developer.getLastName());
        oldDeveloper.setName(developer.getName());
        return new ResponseEntity<>(developerService.update(oldDeveloper), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/developer", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(Developer developer) {
        if (developerService.delete(developer)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/api/developer/current", method = RequestMethod.GET)
    public ResponseEntity<Developer> getCurrent() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        Developer current = developerService.findByUsername(userName);
        if (current == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
    public ResponseEntity<Void> resetPassword(@RequestParam("email") String email) {
        Developer developer = developerService.findByEmail(email);
        if (developer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = developerService.createPasswordResetTokenForDeveloper(developer, token);
        if (resetToken != null) {
            notifier.restorePassword(developer, resetToken);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.POST)
    public ResponseEntity<Void> changePassword(@RequestParam("email") String email,
                                               @RequestParam("token") String token,
                                               @RequestParam("password") String password) {
        Developer developer = developerService.findByEmail(email);
        if (developer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        developer.setPassword(password);
        if (developerService.checkPasswordResetToken(developer, token)) {
            developerService.update(developer);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}


