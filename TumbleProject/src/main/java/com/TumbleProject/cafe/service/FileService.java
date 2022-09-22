package com.TumbleProject.cafe.service;

import com.TumbleProject.cafe.domain.Files;
import com.TumbleProject.cafe.repository.FilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {

    private final FilesRepository filesRepository;

    @Transactional
    public void save(Files files){
        Files f = new Files();
        f.setFilename(files.getFilename());
        f.setFileOriName(files.getFileOriName());
        f.setFileUrl(files.getFileUrl());

        filesRepository.save(f);
    }

}
