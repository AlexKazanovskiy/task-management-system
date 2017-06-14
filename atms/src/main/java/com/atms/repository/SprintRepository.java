package com.atms.repository;

import com.atms.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO class for interaction between application and {@link com.atms.model.Sprint Sprint} table in DB
 *
 * @author Alex Kazanovskiy
 */

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Integer> {
}
