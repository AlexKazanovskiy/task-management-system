package com.atms.repository;

import com.atms.model.DevType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO class for interaction between application and {@link com.atms.model.DevType DevType} table in DB
 *
 * @author Alex Kazanovskiy
 */

@Repository
public interface DevTypeRepository extends JpaRepository<DevType, Integer> {
}
