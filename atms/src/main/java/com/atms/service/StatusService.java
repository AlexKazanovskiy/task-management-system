package com.atms.service;

import com.atms.model.Status;

import java.util.List;


/**
 * Interface of business layer that describe work with Status entity
 *
 * @author Alex Kazanovskiy
 */
public interface StatusService {

    Status save(Status status);

    Status update(Status status);

    Status findOne(Integer id);

    List<Status> findAll();

    boolean delete(Integer id);
}
