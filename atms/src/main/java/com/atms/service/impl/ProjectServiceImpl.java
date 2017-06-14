package com.atms.service.impl;

import com.atms.model.Developer;
import com.atms.model.Project;
import com.atms.repository.ProjectRepository;
import com.atms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Alex Kazanovskiy.
 */

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project save(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public Project update(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public Project findOne(Integer id) {
        return projectRepository.findOne(id);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findByDeveloper(Developer developer) {
        return projectRepository.findBySprintsTasksDeveloper(developer);
    }

    @Override
    public boolean delete(Integer id) {
        if (projectRepository.exists(id)) {
            projectRepository.delete(id);
            return true;
        }
        return false;
    }
}
