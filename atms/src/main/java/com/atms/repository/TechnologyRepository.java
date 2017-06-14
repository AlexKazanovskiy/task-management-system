package com.atms.repository;

import com.atms.model.Developer;
import com.atms.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO class for interaction between application and {@link com.atms.model.Technology Technology} table in DB
 *
 * @author Alex Kazanovskiy
 */

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer> {

    List<Technology> findByRequirementsTasksDeveloper(Developer developer);
}
