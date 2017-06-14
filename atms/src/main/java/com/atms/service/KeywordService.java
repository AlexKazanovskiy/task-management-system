package com.atms.service;

import com.atms.model.Keyword;

import java.util.List;

/**
 * Interface of business layer that describe work with Keyword entity
 *
 * @author Alex Kazanovskiy
 */
public interface KeywordService {

    Keyword save(Keyword keyword);

    Keyword update(Keyword keyword);

    Keyword findOne(Integer id);

    List<Keyword> findAll();

    boolean delete(Integer id);
}
