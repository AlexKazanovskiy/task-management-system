package com.atms.service;

import com.atms.model.Type;

import java.util.List;

/**
 * Interface of business layer that describe work with Type entity
 *
 * @author Alex Kazanovskiy
 */
public interface TypeService {

    Type save(Type type);

    Type update(Type type);

    Type findOne(Integer id);

    List<Type> findAll();

    boolean delete(Integer id);
}
