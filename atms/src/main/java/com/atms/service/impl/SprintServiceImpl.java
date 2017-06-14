package com.atms.service.impl;

import com.atms.model.Sprint;
import com.atms.repository.SprintRepository;
import com.atms.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */

@Service
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;

    @Autowired
    public SprintServiceImpl(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Override
    public Sprint save(Sprint sprint) {
        return sprintRepository.saveAndFlush(sprint);
    }

    @Override
    public Sprint update(Sprint sprint) {
        return sprintRepository.saveAndFlush(sprint);
    }

    @Override
    public Sprint findOne(Integer id) {
        return sprintRepository.findOne(id);
    }

    @Override
    public List<Sprint> findAll() {
        return sprintRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        if (sprintRepository.exists(id)) {
            sprintRepository.delete(id);
            return true;
        }
        return false;
    }
}
