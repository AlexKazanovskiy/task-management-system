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

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Alex Kazanovskiy.
 */

@RestController
public class TaskController {
    private final TaskService taskService;
    private final ProjectService projectService;
    private final StatusService statusService;
    private final PriorityService priorityService;
    private final DeveloperEffectivenessService developerEffectivenessService;
    private static ObjectMapper objectMapper = new ObjectMapper();

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
    public ResponseEntity<Task> get(@PathVariable("id") String id) {
        Task task = taskService.findOne(Integer.parseInt(id));
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, OK);
    }

    @RequestMapping(value = "/api/task/project/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getByProject(@PathVariable("projectId") String projectId) {
        Project project = projectService.findOne(Integer.parseInt(projectId));
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Task> tasks = taskService.findByProject(project);
        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, OK);
    }

    @RequestMapping(value = "/api/task/project/{projectId}/status/{statusId}", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getByProjectAndStatus(@PathVariable("projectId") String projectId,
                                                            @PathVariable("statusId") String statusId) {
        Project project = projectService.findOne(Integer.parseInt(projectId));
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Status status = statusService.findOne(Integer.parseInt(statusId));
        if (status == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Task> tasks = taskService.findByProjectAndStatus(project, status);
        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, OK);
    }

    @RequestMapping(value = "/api/task/project/{projectId}/priority/{priorityId}", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getByProjectAndPriority(@PathVariable("projectId") String projectId,
                                                              @PathVariable("priorityId") String priorityId) {
        Project project = projectService.findOne(Integer.parseInt(projectId));
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Priority priority = priorityService.findOne(Integer.parseInt(priorityId));
        if (priority == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<Task> update(@RequestBody String body, @PathVariable("taskId") String taskId) {
        Task task;
        try {
            task = objectMapper.readValue(body, Task.class);
        } catch (IOException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return new ResponseEntity<>(taskService.update(Integer.parseInt(taskId), task), OK);
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
    public ResponseEntity<Task> close(@PathVariable("taskId") String taskId, @RequestParam("closeTime") Timestamp closeTime) {
        Task task = taskService.findOne(Integer.parseInt(taskId));
        if (task == null)
            return new ResponseEntity<>(NO_CONTENT);

        task.setCloseTime(closeTime);
        for (TaskKeyword keyword : task.getKeywords()) {
            developerEffectivenessService.save(new DeveloperEffectiveness(task.getDeveloper(), keyword.getKeyword(), (double) (task.getActualTime()) / task.getEstimationTime()));
        }
        return new ResponseEntity<>(taskService.close(task), OK);
    }


}
