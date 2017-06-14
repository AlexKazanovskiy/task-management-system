package com.atms.repository;

import com.atms.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO class for interaction between application and {@link com.atms.model.Status Status} table in DB
 *
 * @author Alex Kazanovskiy
 */

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
}
