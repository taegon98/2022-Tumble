package com.TumbleProject.community.service;

import com.TumbleProject.community.entity.Board;
import com.TumbleProject.community.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {

    private static Integer sequence=0;
    @Autowired
    private CommunityRepository communityRepository;

    public void write(Board board) {
        board.setId(++sequence);
        communityRepository.save(board);

    }

    public List<Board> boardList() {
        return communityRepository.findAll();
    }

    public Board boardView(Integer id) {
        return communityRepository.findById(id).get();
    }
}
