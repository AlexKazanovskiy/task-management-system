package com.atms.service.impl;

import com.atms.model.DeveloperEffectiveness;
import com.atms.repository.DeveloperEffectivenessRepository;
import com.atms.service.DeveloperEffectivenessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */
@Service
public class DeveloperEffectivenessServiceImpl implements DeveloperEffectivenessService {

    private final DeveloperEffectivenessRepository developerEffectivenessRepository;

    @Autowired
    public DeveloperEffectivenessServiceImpl(DeveloperEffectivenessRepository developerEffectivenessRepository) {
        this.developerEffectivenessRepository = developerEffectivenessRepository;
    }

    @Override
    public DeveloperEffectiveness save(DeveloperEffectiveness developerEffectiveness) {
        return developerEffectivenessRepository.saveAndFlush(developerEffectiveness);
    }

    @Override
    public DeveloperEffectiveness findOne(Integer developerEffectivenessId) {
        return developerEffectivenessRepository.findOne(developerEffectivenessId);
    }

    @Override
    public List<DeveloperEffectiveness> findAll() {
        return developerEffectivenessRepository.findAll();
    }

    @Override
    public DeveloperEffectiveness update(DeveloperEffectiveness developerEffectiveness) {
        return developerEffectivenessRepository.saveAndFlush(developerEffectiveness);
    }

    @Override
    public boolean delete(Integer developerEffectivenessId) {
        if (developerEffectivenessRepository.findOne(developerEffectivenessId) == null)
            return false;
        developerEffectivenessRepository.delete(developerEffectivenessId);
        return true;
    }
}
