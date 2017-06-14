package com.atms.service;

import com.atms.model.Priority;
import com.atms.model.Project;
import com.atms.model.Status;
import com.atms.model.Task;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface of business layer that describe work with Task entity
 *
 * @author Alex Kazanovskiy
 */
public interface TaskService {

    Task save(Task task);

    Task update(Integer id, Task task);

    Task findOne(Integer id);

    Task close(Task task);

    List<Task> findAll();

    List<Task> findAll(List<Integer> taskIds);

    /**
     * Find all task of project with concrete priority
     *
     * @param project  object of Project
     * @param priority object of Priority
     * @return List of Task
     */
    List<Task> findByProjectAndPriority(Project project, Priority priority);

    /**
     * Find all task of project with concrete status
     *
     * @param project object of Project
     * @param status  object of Status
     * @return List of Task
     */
    List<Task> findByProjectAndStatus(Project project, Status status);

    /**
     * Find all task of project
     *
     * @param project object of Project
     * @return List of Task
     */
    List<Task> findByProject(Project project);

    List<Task> findByStartTimeInInterval(Timestamp start, Timestamp end);

    List<Task> findByDeadlineLess(Timestamp timestamp);

    List<Task> findByTitleContaining(String title);
}
