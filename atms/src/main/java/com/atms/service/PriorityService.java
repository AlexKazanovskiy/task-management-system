package com.atms.service;

import com.atms.model.Priority;

import java.util.List;

/**
 * Interface of business layer that describe work with Priority entity
 *
 * @author Alex Kazanovskiy
 */
public interface PriorityService {

    Priority save(Priority priority);

    Priority update(Priority priority);

    Priority findOne(Integer id);

    List<Priority> findAll();

    boolean delete(Integer id);
}
