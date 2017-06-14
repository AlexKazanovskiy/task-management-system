package com.atms.service.impl;

import com.atms.model.Requirement;
import com.atms.repository.RequirementsRepository;
import com.atms.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */

@Service
public class RequirementServiceImpl implements RequirementService {

    private final RequirementsRepository requirementsRepository;

    @Autowired
    public RequirementServiceImpl(RequirementsRepository requirementsRepository) {
        this.requirementsRepository = requirementsRepository;
    }

    @Override
    public Requirement save(Requirement requirement) {
        return requirementsRepository.saveAndFlush(requirement);
    }

    @Override
    public Requirement update(Requirement requirement) {
        return requirementsRepository.saveAndFlush(requirement);
    }

    @Override
    public Requirement findOne(Integer id) {
        return requirementsRepository.findOne(id);
    }

    @Override
    public List<Requirement> findAll() {
        return requirementsRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        if (requirementsRepository.exists(id)) {
            requirementsRepository.delete(id);
            return true;
        }
        return false;

    }
}

