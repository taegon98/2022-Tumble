package com.TumbleProject.cafe.repository;

import com.TumbleProject.cafe.domain.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<Cafe, Integer> {
}
