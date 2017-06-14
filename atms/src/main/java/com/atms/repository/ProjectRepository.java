package com.atms.repository;

import com.atms.model.Developer;
import com.atms.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO class for interaction between application and {@link com.atms.model.Project Project} table in DB
 *
 * @author Alex Kazanovskiy
 */

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    /**
     * Find all project in which developer works
     *
     * @param developer object of {@link com.atms.model.Developer Developer}
     * @return list of {@link com.atms.model.Project Project}
     */
    List<Project> findBySprintsTasksDeveloper(Developer developer);

}