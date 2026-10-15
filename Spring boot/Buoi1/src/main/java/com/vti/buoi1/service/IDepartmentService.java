package com.vti.buoi1.service;

import com.vti.buoi1.entity.Department;

import java.util.List;

public interface IDepartmentService  {
    List<Department> findAll();

    Department findById(Integer id);

    void create(Department department);

    void update(Integer id, Department department);

    void deleteById(Integer id);
}
