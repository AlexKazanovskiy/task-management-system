package com.atms.service.impl;

import com.atms.model.Developer;
import com.atms.model.Technology;
import com.atms.repository.TechnologyRepository;
import com.atms.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public Technology save(Technology technology) {
        return technologyRepository.saveAndFlush(technology);
    }

    @Override
    public Technology update(Technology technology) {
        return technologyRepository.save(technology);
    }

    @Override
    public Technology findOne(Integer id) {
        return technologyRepository.findOne(id);
    }

    @Override
    public List<Technology> findAll() {
        return technologyRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        if (technologyRepository.exists(id)) {
            technologyRepository.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Technology> getByDeveloper(Developer developer) {
        return technologyRepository.findByRequirementsTasksDeveloper(developer);
    }
}
