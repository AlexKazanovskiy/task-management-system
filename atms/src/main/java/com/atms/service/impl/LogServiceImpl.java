package com.atms.service.impl;

import com.atms.model.Log;
import com.atms.repository.LogRepository;
import com.atms.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */
@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Log save(Log log) {
        return logRepository.saveAndFlush(log);
    }

    @Override
    public Log update(Log log) {
        return logRepository.saveAndFlush(log);
    }

    @Override
    public Log findOne(Integer id) {
        return logRepository.findOne(id);
    }

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        if (logRepository.findOne(id) == null)
            return false;
        logRepository.delete(id);
        return true;
    }
}
