package com.TumbleProject.help.repository;

import com.TumbleProject.help.entity.helpBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface helpRepository extends JpaRepository<helpBoard,Integer> {

    Page<helpBoard> findByTitleContaining(String searchKeyword, Pageable pageable);
}
