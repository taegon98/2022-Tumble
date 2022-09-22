package com.TumbleProject.community.repository;

import com.TumbleProject.community.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Board,Integer> {

    @Modifying
    @Query("update Board p set p.countVisit = p.countVisit +1 where p.id= :id")
    int updateCount(Integer id);

    Page<Board> findByTitleContaining(String searchKeyword,Pageable paging);


}
