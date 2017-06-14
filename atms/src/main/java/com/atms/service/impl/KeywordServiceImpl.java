package com.atms.service.impl;

import com.atms.model.Keyword;
import com.atms.repository.KeywordRepository;
import com.atms.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Alex Kazanovskiy.
 */

@Service
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository keywordRepository;

    @Autowired
    public KeywordServiceImpl(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    @Override
    public Keyword save(Keyword keyword) {
        return keywordRepository.saveAndFlush(keyword);
    }

    @Override
    public Keyword update(Keyword keyword) {
        return keywordRepository.saveAndFlush(keyword);
    }

    @Override
    public Keyword findOne(Integer id) {
        return keywordRepository.findOne(id);
    }

    @Override
    public List<Keyword> findAll() {
        return keywordRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {

        if (keywordRepository.exists(id)) {
            keywordRepository.delete(id);
            return true;
        }
        return false;
    }
}
