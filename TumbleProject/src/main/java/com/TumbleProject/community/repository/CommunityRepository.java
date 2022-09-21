package com.TumbleProject.community.repository;

import com.TumbleProject.community.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Board,Integer> {
}