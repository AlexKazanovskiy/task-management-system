package com.atms.repository;

import com.atms.model.TaskKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex Kazanovskiy.
 */
@Repository
public interface TaskKeywordRepository extends JpaRepository<TaskKeyword, Integer> {
}
