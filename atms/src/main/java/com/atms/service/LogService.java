package com.atms.service;

import com.atms.model.Log;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */
public interface LogService {

    Log save(Log log);

    Log update(Log log);

    Log findOne(Integer id);

    List<Log> findAll();

    boolean delete(Integer id);
}
