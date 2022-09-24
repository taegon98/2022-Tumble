package com.TumbleProject.Controller.cafe.service;

import com.TumbleProject.Controller.cafe.domain.Files;
import com.TumbleProject.Controller.cafe.repository.FilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Files> findFiles(){
        return filesRepository.findAll();
    }

}
