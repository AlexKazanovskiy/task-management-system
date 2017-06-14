package com.atms.service;

import com.atms.model.Document;

import java.util.List;

/**
 * Interface of business layer that describe work with Document entity
 *
 * @author Alex Kazanovskiy
 */
public interface DocumentService {

    Document save(Document document);

    Document update(Document document);

    Document findOne(Integer id);

    List<Document> findAll();

    boolean delete(Integer id);
}
