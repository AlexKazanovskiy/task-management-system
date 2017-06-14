package com.atms.service;

import com.atms.model.TaskKeyword;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */
public interface TaskKeywordService {

    TaskKeyword findOne(Integer taskKeywordId);

    TaskKeyword save(TaskKeyword taskKeyword);

    List<TaskKeyword> findAll();

    TaskKeyword update(TaskKeyword taskKeyword);

    boolean delete(Integer taskKeywordId);
}
