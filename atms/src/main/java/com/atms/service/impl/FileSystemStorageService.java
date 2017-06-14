package com.atms.service.impl;

import com.atms.service.StorageService;
import com.atms.storage.StorageException;
import com.atms.storage.StorageFileNotFoundException;
import com.atms.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    private final String[] extensions;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        extensions = new String[]{"image/jpeg", "image/bmp", "image/pjpeg", "image/png", "image/gif", "text/plain", "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "application/pdf", "application/xml"};
    }

    @Override
    public String store(String taskId, MultipartFile file) throws StorageException {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
        }
        String link = taskId + "/" + file.getOriginalFilename();
        createDirectory(this.rootLocation.toString() + "/" + taskId);
        if (verifyExtension(file.getContentType()))
            try {
                Files.copy(file.getInputStream(), this.rootLocation.resolve(link));
                return link;
            } catch (IOException e) {
                throw new StorageException("File already exist");
            }
        else
            throw new StorageException("Failed to store file with extension: " + file.getContentType());

    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    private boolean verifyExtension(String fileExtension) {
        for (String extension : extensions) {
            if (extension.equals(fileExtension))
                return true;
        }
        return false;
    }

    private void createDirectory(String directory) {
        try {
            Files.createDirectories(Paths.get(directory));
        } catch (IOException e) {
            throw new StorageException("Could not create directory", e);
        }
    }
}
