package com.atms.repository;

import com.atms.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alex Kazanovskiy.
 */
public interface LogRepository extends JpaRepository<Log, Integer> {
}
