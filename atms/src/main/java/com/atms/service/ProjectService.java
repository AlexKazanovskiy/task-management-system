package com.atms.service;

import com.atms.model.Developer;
import com.atms.model.Project;

import java.util.List;

/**
 * Interface of business layer that describe work with Project entity
 *
 * @author Alex Kazanovskiy
 */
public interface ProjectService {

    Project save(Project project);

    Project update(Project project);

    Project findOne(Integer id);

    List<Project> findAll();

    /**
     * Find all project of concrete developer
     *
     * @param developer object of entity Developer
     * @return List of Projects
     */
    List<Project> findByDeveloper(Developer developer);

    boolean delete(Integer id);
}
