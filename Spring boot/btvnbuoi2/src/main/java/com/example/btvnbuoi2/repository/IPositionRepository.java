package com.example.btvnbuoi2.repository;

import com.example.btvnbuoi2.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IPositionRepository extends JpaRepository<Position, Integer> {
}
