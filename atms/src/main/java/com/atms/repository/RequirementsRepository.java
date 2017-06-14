package com.atms.repository;

import com.atms.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO class for interaction between application and {@link com.atms.model.Requirement Requirement} table in DB
 *
 * @author Alex Kazanovskiy
 */

@Repository
public interface RequirementsRepository extends JpaRepository<Requirement, Integer> {
}
