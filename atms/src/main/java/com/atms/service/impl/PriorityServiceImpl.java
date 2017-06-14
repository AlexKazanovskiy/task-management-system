package com.atms.service.impl;

import com.atms.model.Priority;
import com.atms.repository.PriorityRepository;
import com.atms.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */

@Service
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepository priorityRepository;

    @Autowired
    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public Priority save(Priority priority) {
        return priorityRepository.saveAndFlush(priority);
    }

    @Override
    public Priority update(Priority priority) {
        return priorityRepository.saveAndFlush(priority);
    }

    @Override
    public Priority findOne(Integer id) {
        return priorityRepository.findOne(id);
    }

    @Override
    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        if (priorityRepository.exists(id)) {
            priorityRepository.delete(id);
            return true;
        }
        return false;
    }
}
