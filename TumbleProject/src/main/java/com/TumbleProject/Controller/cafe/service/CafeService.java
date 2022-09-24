package com.TumbleProject.Controller.cafe.service;

import com.TumbleProject.Controller.cafe.domain.Cafe;
import com.TumbleProject.Controller.cafe.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
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

    public void boardDelete(Integer id) {
        cafeRepository.deleteById(id);
    }

}
