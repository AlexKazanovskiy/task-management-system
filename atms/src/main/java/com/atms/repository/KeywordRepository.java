package com.atms.repository;

import com.atms.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO class for interaction between application and {@link com.atms.model.Keyword Keyword} table in DB
 *
 * @author Alex Kazanovskiy
 */

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
}
