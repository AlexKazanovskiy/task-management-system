package com.atms.service;

import com.atms.model.DeveloperEffectiveness;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */
public interface DeveloperEffectivenessService {

    DeveloperEffectiveness save(DeveloperEffectiveness developerEffectiveness);

    DeveloperEffectiveness findOne(Integer developerEffectivenessId);

    List<DeveloperEffectiveness> findAll();

    DeveloperEffectiveness update(DeveloperEffectiveness developerEffectiveness);

    boolean delete(Integer developerEffectivenessId);
}
