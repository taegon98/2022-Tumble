package com.TumbleProject.community.service;

import com.TumbleProject.community.entity.Board;
import com.TumbleProject.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {
    
    private final CommunityRepository communityRepository;

    public void write(Board board) {
        communityRepository.save(board);
    }

    public List<Board> boardList() {
        return communityRepository.findAll();
    }

    public Board boardView(Integer id) {
        return communityRepository.findById(id).get();
    }

    public void boardDelete(Integer id) {
        communityRepository.deleteById(id);
    }
}
