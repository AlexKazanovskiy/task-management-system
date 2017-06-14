package com.atms.repository;

import com.atms.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO class for interaction between application and {@link com.atms.model.Priority Priority} table in DB
 *
 * @author Alex Kazanovskiy
 */

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
