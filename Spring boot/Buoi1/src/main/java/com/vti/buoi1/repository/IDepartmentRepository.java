package com.vti.buoi1.repository;

import com.vti.buoi1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
                                            // objects Jpa ho tro
public interface IDepartmentRepository extends JpaRepository<Department,Integer> {
}
