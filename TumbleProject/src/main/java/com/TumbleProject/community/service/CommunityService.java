package com.TumbleProject.community.service;

import com.TumbleProject.community.entity.Board;
import com.TumbleProject.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityService {


    private final CommunityRepository communityRepository;

    public Board write(Board board) {
        String dateFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm"));
        board.setWriteDate(dateFormat);
        board.setCountVisit(0);
        communityRepository.save(board);

        // 이부분에 board.setWriter(사용자 id) 으로 와야함 태곤 태곤
        return board;
    }

    public Page<Board> boardList(Pageable pageable) {

        return communityRepository.findAll(pageable);
    }

    public Board boardView(Integer id) {
        return communityRepository.findById(id).get();
    }

    public void boardDelete(Integer id) {
        communityRepository.deleteById(id);
    }

    @Transactional
    public int updateView(Integer id) {
        return communityRepository.updateCount(id);
    }

}
