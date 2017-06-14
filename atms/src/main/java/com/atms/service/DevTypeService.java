package com.atms.service;

import com.atms.model.DevType;

import java.util.List;

/**
 * Interface of business layer that describe work with Developer entity
 *
 * @author Alex Kazanovskiy
 */
public interface DevTypeService {

    DevType save(DevType devType);

    DevType update(DevType devType);

    DevType findOne(Integer id);

    List<DevType> findAll();

    boolean delete(Integer id);
}
