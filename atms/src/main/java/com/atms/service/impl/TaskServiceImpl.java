package com.atms.service.impl;

import com.atms.model.*;
import com.atms.notify.Notifier;
import com.atms.repository.DeveloperEffectivenessRepository;
import com.atms.repository.TaskRepository;
import com.atms.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final DeveloperEffectivenessRepository developerEffectivenessRepository;
    private final Notifier notifier;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, DeveloperEffectivenessRepository developerEffectivenessRepository, Notifier notifier) {
        this.taskRepository = taskRepository;
        this.developerEffectivenessRepository = developerEffectivenessRepository;
        this.notifier = notifier;
    }

    @Override
    public Task save(Task task) {
        if (task.getDeveloper() != null) {
            notifier.notifyDeveloper(task);
        }
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public Task update(Integer id, Task task) {
        Task oldTask = taskRepository.findOne(id);
        if (oldTask == null) {
            return null;
        }

        if (!oldTask.getStatus().equals(task.getStatus()))
            notifier.notifyCustomer(task);

        if (!oldTask.getDeveloper().equals(task.getDeveloper()))
            notifier.notifyDeveloper(task);

        oldTask.setTitle(task.getTitle());
        oldTask.setDescription(task.getDescription());
        oldTask.setDateStart(task.getDateStart());
        oldTask.setDeadline(task.getDeadline());
        oldTask.setVersion(task.getVersion());
        oldTask.setAssignedTime(task.getAssignedTime());
        oldTask.setCloseTime(oldTask.getCloseTime());
        oldTask.setParent(task.getParent());
        oldTask.setSubtasks(task.getSubtasks());
        oldTask.setPriority(task.getPriority());
        oldTask.setType(task.getType());
        oldTask.setStatus(task.getStatus());
        oldTask.setSprint(task.getSprint());
        oldTask.setDeveloper(task.getDeveloper());
        oldTask.setDocuments(task.getDocuments());
        return taskRepository.save(task);
    }

    @Override
    public Task findOne(Integer id) {
        return taskRepository.findOne(id);
    }

    @Override
    public Task close(Task task) {
        DeveloperEffectiveness developerEffectiveness;
        for (TaskKeyword k : task.getKeywords()) {
            developerEffectiveness = developerEffectivenessRepository.findByKeyword(k.getKeyword());
            if (developerEffectiveness == null)
                developerEffectiveness = developerEffectivenessRepository.saveAndFlush(new DeveloperEffectiveness(task.getDeveloper(), k.getKeyword(), ((double) task.getActualTime() / (double) task.getEstimationTime())));
            else
                developerEffectiveness.setDeviation(developerEffectiveness.getDeviation() + task.getActualTime() / task.getEstimationTime());
            task.getDeveloper().getDeveloperEffectiveness().
                    add(developerEffectiveness);
        }
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAll(List<Integer> taskIds) {
        return taskRepository.findAll(taskIds);
    }

    @Override
    public List<Task> findByProjectAndPriority(Project project, Priority priority) {
        return taskRepository.findBySprintProjectAndPriority(project, priority);
    }

    @Override
    public List<Task> findByProjectAndStatus(Project project, Status status) {
        return taskRepository.findBySprintProjectAndStatus(project, status);
    }

    @Override
    public List<Task> findByProject(Project project) {
        return taskRepository.findBySprintProject(project);
    }

    @Override
    public List<Task> findByStartTimeInInterval(Timestamp start, Timestamp end) {
        return taskRepository.findByDateStartGreaterThanEqualAndDateStartLessThanEqual(start, end);
    }

    @Override
    public List<Task> findByDeadlineLess(Timestamp timestamp) {
        return taskRepository.findByDeadlineIsLessThanEqual(timestamp);
    }

    @Override
    public List<Task> findByTitleContaining(String title) {
        return taskRepository.findByTitleContaining(title);
    }
}
