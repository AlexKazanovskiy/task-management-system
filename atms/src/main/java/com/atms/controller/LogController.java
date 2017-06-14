package com.atms.controller;

import com.atms.model.Log;
import com.atms.model.Task;
import com.atms.service.LogService;
import com.atms.service.StorageService;
import com.atms.service.TaskService;
import com.atms.storage.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 * @author Alex Kazanovskiy.
 */
@RestController
public class LogController {
    private final LogService logService;
    private final TaskService taskService;
    private final StorageService storageService;

    @Autowired
    public LogController(LogService logService, TaskService taskService, StorageService storageService) {
        this.logService = logService;
        this.taskService = taskService;
        this.storageService = storageService;
    }

    @RequestMapping(value = "/api/log/task/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<Set<Log>> getLogsByTask(@PathVariable("taskId") String taskId) {
        Task task = taskService.findOne(Integer.parseInt(taskId));
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Set<Log> logs = task.getLogs();
        if (logs == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/log/task/{taskId}", method = RequestMethod.POST)
    public ResponseEntity<Log> add(@RequestParam("file") MultipartFile file,
                                   @RequestParam("applications") String applications,
                                   @PathVariable("taskId") Integer taskId) {
        Log log = new Log();
        Task task = taskService.findOne(taskId);
        if (task == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String link;
        try {
            link = storageService.store(String.valueOf(task.getTaskId()) + "/log", file);
        } catch (StorageException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.setTask(task);
        log.setApplications(applications);
        log.setLink(link);
        return new ResponseEntity<>(logService.save(log), HttpStatus.OK);
    }
}
