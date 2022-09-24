package com.TumbleProject.Controller.cafe.repository;

import com.TumbleProject.Controller.cafe.domain.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<Cafe, Integer> {
}
