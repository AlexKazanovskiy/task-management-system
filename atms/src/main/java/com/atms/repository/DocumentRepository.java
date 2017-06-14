package com.atms.repository;

import com.atms.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO class for interaction between application and {@link com.atms.model.Document Document} table in DB
 *
 * @author Alex Kazanovskiy
 */

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
