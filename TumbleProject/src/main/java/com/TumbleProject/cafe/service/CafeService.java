package com.TumbleProject.cafe.service;

import com.TumbleProject.cafe.domain.Cafe;
import com.TumbleProject.cafe.repository.CafeRepository;
import com.TumbleProject.community.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeService {

    private final CafeRepository cafeRepository;

    /*
    카페 작성
     */
    @Transactional
    public Integer join(Cafe cafe) {
        cafeRepository.save(cafe);
        return cafe.getId();
    }


    /*
    전체 카페 리스트
     */

    public List<Cafe> findCafes(){
        return cafeRepository.findAll();
    }

    public Cafe findCafeOne(Integer id) {
        return cafeRepository.findById(id).get();
    }


}
