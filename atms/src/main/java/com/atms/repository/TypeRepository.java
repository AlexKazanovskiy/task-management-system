package com.atms.repository;

import com.atms.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO class for interaction between application and {@link com.atms.model.Type Type} table in DB
 *
 * @author Alex Kazanovskiy
 */
@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
}
