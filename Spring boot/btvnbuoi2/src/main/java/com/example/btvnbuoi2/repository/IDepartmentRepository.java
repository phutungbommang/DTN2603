package com.example.btvnbuoi2.repository;

import com.example.btvnbuoi2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

// objects Jpa ho tro
public interface IDepartmentRepository extends JpaRepository<Department,Integer> {
}
