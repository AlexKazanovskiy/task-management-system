package com.atms.repository;

import com.atms.model.Developer;
import com.atms.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO class for interaction between application and {@link com.atms.model.Developer Developer} table in DB
 *
 * @author Alex Kazanovskiy
 */

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

    /**
     * Find all developers of concrete project
     *
     * @param project object of {@link com.atms.model.Project Project}
     * @return list of {@link com.atms.model.Developer Developer}
     */
    List<Developer> findByTasksAsDeveloperSprintProject(Project project);

    Developer findByNickname(String nickname);

    Developer findByEmail(String email);
}
