package com.vti.buoi1.controller;

import com.vti.buoi1.entity.Department;
import com.vti.buoi1.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

    @Autowired// khoi tao object
    private IDepartmentService departmentService;


    @GetMapping
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Department findById(@PathVariable Integer id) {
        return departmentService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody Department department) {
        departmentService.create(department);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable Integer id, @RequestBody Department department) {
        departmentService.update(id, department);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Integer id) {
        departmentService.deleteById(id);
    }
}
