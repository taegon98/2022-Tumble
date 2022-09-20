package com.TumbleProject.community.repository;

import com.TumbleProject.community.domain.Board;

import java.util.HashMap;
import java.util.Map;

public class CommunityMemoryRepository implements CommunityRepository{

    private static Map<Long,Board> store=new HashMap<>();
    private static long sequence=0L;

    @Override
    public Board enroll(Board board) {
        board.setBoardNum(++sequence);
        return board;
    }
}
