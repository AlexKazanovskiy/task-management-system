package com.atms.service.impl;

import com.atms.model.Document;
import com.atms.repository.DocumentRepository;
import com.atms.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document save(Document document) {
        return documentRepository.saveAndFlush(document);
    }

    @Override
    public Document update(Document document) {
        return documentRepository.saveAndFlush(document);
    }

    @Override
    public Document findOne(Integer id) {
        return documentRepository.findOne(id);
    }

    @Override
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        if (documentRepository.exists(id)) {
            documentRepository.delete(id);
            return true;
        }
        return false;
    }
}
