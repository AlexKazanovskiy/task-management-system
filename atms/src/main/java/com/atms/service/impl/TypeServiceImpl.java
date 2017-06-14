package com.atms.service.impl;

import com.atms.model.Type;
import com.atms.repository.TypeRepository;
import com.atms.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Kazanovskiy.
 */

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Type save(Type type) {
        return typeRepository.saveAndFlush(type);
    }

    @Override
    public Type update(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type findOne(Integer id) {
        return typeRepository.findOne(id);
    }

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        if (typeRepository.exists(id)) {
            typeRepository.delete(id);
            return true;
        }
        return false;
    }
}
