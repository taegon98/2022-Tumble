package com.TumbleProject.cafe.service;

import com.TumbleProject.cafe.domain.Cafe;
import com.TumbleProject.cafe.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeService {

    private final CafeRepository cafeRepository;

    /*
    카페 작성
     */
    @Transactional
    public Long join(Cafe cafe) {
        cafeRepository.save(cafe);
        return cafe.getId();
    }

    /*
    전체 카페 리스트
     */

    public List<Cafe> findCafes(){
        return cafeRepository.findAll();
    }

}
