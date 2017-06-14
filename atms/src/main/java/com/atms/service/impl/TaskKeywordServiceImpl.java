package com.atms.service.impl;

import com.atms.model.TaskKeyword;
import com.atms.repository.TaskKeywordRepository;
import com.atms.service.TaskKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */
@Service
public class TaskKeywordServiceImpl implements TaskKeywordService {

    private final TaskKeywordRepository taskKeywordRepository;

    @Autowired
    public TaskKeywordServiceImpl(TaskKeywordRepository taskKeywordRepository) {
        this.taskKeywordRepository = taskKeywordRepository;
    }

    @Override
    public TaskKeyword findOne(Integer taskKeywordId) {
        return taskKeywordRepository.findOne(taskKeywordId);
    }

    @Override
    public TaskKeyword save(TaskKeyword taskKeyword) {
        return taskKeywordRepository.saveAndFlush(taskKeyword);
    }

    @Override
    public List<TaskKeyword> findAll() {
        return taskKeywordRepository.findAll();
    }

    @Override
    public TaskKeyword update(TaskKeyword taskKeyword) {
        return taskKeywordRepository.saveAndFlush(taskKeyword);
    }

    @Override
    public boolean delete(Integer taskKeywordId) {
        if (taskKeywordRepository.findOne(taskKeywordId) == null)
            return false;
        taskKeywordRepository.delete(taskKeywordId);
        return true;
    }
}