package com.atms.service;

import com.atms.model.Requirement;

import java.util.List;

/**
 * Interface of business layer that describe work with Requirement entity
 *
 * @author Alex Kazanovskiy
 */
public interface RequirementService {

    Requirement save(Requirement requirement);

    Requirement update(Requirement requirement);

    Requirement findOne(Integer id);

    List<Requirement> findAll();

    boolean delete(Integer id);
}

