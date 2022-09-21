package com.TumbleProject.community.repository;

import com.TumbleProject.community.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Board,Integer> {

    @Modifying
    @Query("update Board p set p.view = p.view+1 where p.id = : id")
    int updateView(Long id);
}
