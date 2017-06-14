package com.atms.service;

import com.atms.model.Developer;
import com.atms.model.Technology;

import java.util.List;

/**
 * Interface of business layer that describe work with Technology entity
 *
 * @author Alex Kazanovskiy
 */
public interface TechnologyService {

    Technology save(Technology technology);

    Technology update(Technology technology);

    Technology findOne(Integer id);

    List<Technology> findAll();

    boolean delete(Integer id);

    List<Technology> getByDeveloper(Developer developer);
}
