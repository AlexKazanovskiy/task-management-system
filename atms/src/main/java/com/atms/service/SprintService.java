package com.atms.service;

import com.atms.model.Sprint;

import java.util.List;

/**
 * Interface of business layer that describe work with Sprint entity
 *
 * @author Alex Kazanovskiy
 */
public interface SprintService {

    Sprint save(Sprint sprint);

    Sprint update(Sprint sprint);

    Sprint findOne(Integer id);

    List<Sprint> findAll();

    boolean delete(Integer id);
}
