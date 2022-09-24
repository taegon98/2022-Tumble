package com.TumbleProject.help.service;

import com.TumbleProject.community.entity.Board;
import com.TumbleProject.help.entity.helpBoard;
import com.TumbleProject.help.repository.helpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class helpService {

    private final helpRepository helpRepository;
    private static int pageNum;

    public helpBoard write(helpBoard board) {
        String dateFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm"));
        board.setWriteDate(dateFormat);
        board.setCountVisit(0);
        helpRepository.save(board);

        return board;
    }

    public Page<helpBoard> boardList(Pageable pageable) {

        return helpRepository.findAll(pageable);
    }

    public Page<helpBoard> boardSearchList(String searchKeyword,Pageable pageable) {
        return helpRepository.findByTitleContaining(searchKeyword, pageable);
    }

    public helpBoard boardView(Integer id) {
        return helpRepository.findById(id).get();
    }

    public void boardDelete(Integer id) {
        helpRepository.deleteById(id);
    }

//    @Transactional
//    public int updateView(Integer id) {
//
//    }
}
