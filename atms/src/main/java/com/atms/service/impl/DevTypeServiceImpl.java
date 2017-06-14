package com.atms.service.impl;

import com.atms.model.DevType;
import com.atms.repository.DevTypeRepository;
import com.atms.service.DevTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */

@Service
public class DevTypeServiceImpl implements DevTypeService {

    private final DevTypeRepository devTypeRepository;

    @Autowired
    public DevTypeServiceImpl(DevTypeRepository devTypeRepository) {
        this.devTypeRepository = devTypeRepository;
    }

    @Override
    public DevType save(DevType devType) {
        return devTypeRepository.saveAndFlush(devType);
    }

    @Override
    public DevType update(DevType devType) {
        return devTypeRepository.saveAndFlush(devType);
    }

    @Override
    public DevType findOne(Integer id) {
        return devTypeRepository.findOne(id);
    }

    @Override
    public List<DevType> findAll() {
        return devTypeRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        if (devTypeRepository.exists(id)) {
            devTypeRepository.delete(id);
            return true;
        }
        return false;
    }
}
