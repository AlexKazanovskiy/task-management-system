package com.atms.controller;

import com.atms.model.*;
import com.atms.service.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author Alex Kazanovskiy.
 */

@RestController
public class TaskController {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private final TaskService taskService;
    private final ProjectService projectService;
    private final StatusService statusService;
    private final PriorityService priorityService;
    private final DeveloperEffectivenessService developerEffectivenessService;

    @Autowired
    public TaskController(ProjectService projectService, StatusService statusService,
                          PriorityService priorityService, TaskService taskService,
                          DeveloperEffectivenessService developerEffectivenessService) {
        this.projectService = projectService;
        this.statusService = statusService;
        this.priorityService = priorityService;
        this.taskService = taskService;
        this.developerEffectivenessService = developerEffectivenessService;
    }

    @RequestMapping(value = "/api/task", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAll() {
        List<Task> tasks = taskService.findAll();
        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, OK);
    }

    @RequestMapping(value = "/api/task/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> get(@PathVariable("id") Task task) {
        return new ResponseEntity<>(task, OK);
    }

    @RequestMapping(value = "/api/task/project/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getByProject(@PathVariable("projectId") Project project) {
        List<Task> tasks = taskService.findByProject(project);
        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, OK);
    }

    @RequestMapping(value = "/api/task/project/{projectId}/status/{statusId}", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getByProjectAndStatus(@PathVariable("projectId") Project project,
                                                            @PathVariable("statusId") Status status) {
        List<Task> tasks = taskService.findByProjectAndStatus(project, status);
        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, OK);
    }

    @RequestMapping(value = "/api/task/project/{projectId}/priority/{priorityId}", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getByProjectAndPriority(@PathVariable("projectId") Project project,
                                                              @PathVariable("priorityId") Priority priority) {
        List<Task> tasks = taskService.findByProjectAndPriority(project, priority);
        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, OK);
    }


    @RequestMapping(value = "/api/task", method = RequestMethod.POST)
    public ResponseEntity<Task> add(@RequestBody String body) {
        Task task;
        try {
            task = objectMapper.readValue(body, Task.class);
        } catch (IOException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(taskService.save(task), OK);
    }

    @RequestMapping(value = "/api/task/{taskId}", method = RequestMethod.PUT)
    public ResponseEntity<Task> update(@RequestBody String body, @PathVariable("taskId") Task oldTask) {
        Task task;
        try {
            task = objectMapper.readValue(body, Task.class);
        } catch (IOException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(taskService.update(oldTask, task), OK);
    }

    @RequestMapping(value = "/api/task/search/start", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getStartTimeGreater(@RequestParam("start") Timestamp start,
                                                          @RequestParam("end") Timestamp end) {
        List<Task> tasks = taskService.findByStartTimeInInterval(start, end);
        if (tasks.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, OK);
    }

    @RequestMapping(value = "/api/task/search/title", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getByTitleContaining(@RequestParam("title") String title) {
        List<Task> tasks = taskService.findByTitleContaining(title);
        if (tasks.size() == 0)
            return new ResponseEntity<>(NO_CONTENT);
        return new ResponseEntity<>(tasks, OK);
    }

    @RequestMapping(value = "/api/task/close/{taskId}", method = RequestMethod.POST)
    public ResponseEntity<Task> close(@PathVariable("taskId") Task task, @RequestParam("closeTime") Timestamp closeTime) {
        task.setCloseTime(closeTime);
        for (TaskKeyword keyword : task.getKeywords()) {
            developerEffectivenessService.save(new DeveloperEffectiveness(task.getDeveloper(), keyword.getKeyword(), (double) (task.getActualTime()) / task.getEstimationTime()));
        }
        return new ResponseEntity<>(taskService.close(task), OK);
    }


}
