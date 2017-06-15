package com.atms.controller;


import com.atms.model.Document;
import com.atms.model.Task;
import com.atms.service.DocumentService;
import com.atms.service.StorageService;
import com.atms.service.TaskService;
import com.atms.storage.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * @author Alex Kazanovskiy.
 */

@RestController
public class DocumentController {

    private final StorageService storageService;
    private final DocumentService documentService;
    private final TaskService taskService;

    @Autowired
    public DocumentController(StorageService storageService, DocumentService documentService, TaskService taskService) {
        this.storageService = storageService;
        this.documentService = documentService;
        this.taskService = taskService;
    }

    @RequestMapping(value = "/api/document", method = RequestMethod.GET)
    public ResponseEntity<List<Document>> getAllDocument() {
        List<Document> documents = documentService.findAll();
        if (documents == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/document/task/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<Set<Document>> getDocumentByTask(@PathVariable("taskId") Task task) {
        Set<Document> documents = task.getDocuments();
        if (documents == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/document/task/{taskId}", method = RequestMethod.POST)
    public ResponseEntity<Document> add(@RequestParam("file") MultipartFile file,
                                        @PathVariable("taskId") Task task) {
        String link;
        try {
            link = storageService.store(task.getTaskId() + "/documents", file);
        } catch (StorageException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Document document = new Document();
        document.setLink(link);
        document.setTask(task);
        return new ResponseEntity<>(documentService.save(document), HttpStatus.OK);
    }
}