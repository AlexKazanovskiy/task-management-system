package com.atms.service.impl;

import com.atms.model.Status;
import com.atms.repository.StatusRepository;
import com.atms.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status save(Status status) {
        return statusRepository.saveAndFlush(status);
    }

    @Override
    public Status update(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status findOne(Integer id) {
        return statusRepository.findOne(id);
    }

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        if (statusRepository.exists(id)) {
            statusRepository.delete(id);
            return true;
        }
        return false;
    }
}
