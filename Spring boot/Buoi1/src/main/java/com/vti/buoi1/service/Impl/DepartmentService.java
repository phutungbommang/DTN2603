package com.vti.buoi1.service.Impl;

import com.vti.buoi1.entity.Department;
import com.vti.buoi1.repository.IDepartmentRepository;
import com.vti.buoi1.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentService implements IDepartmentService{
    @Autowired
    private IDepartmentRepository departmentRepository;


    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Department department) {
        departmentRepository.save(department);// lưu department vào DB
    }

    @Override
    public void update(Integer id, Department departmentUpdate) {
        // tìm department theo id muốn update
        Department dep = departmentRepository.findById(id).orElse(null);
        if (Objects.isNull(dep)) {// id ko đúng
            throw new RuntimeException("ID ko tồn tại!");
        }

        // update
        dep.setName(departmentUpdate.getName());
        // luu lai
        departmentRepository.save(dep);
    }

    @Override
    public void deleteById(Integer id) {
        // nesu id tòn tai thì xoa

        // neus ko tòn tai thi bao
        departmentRepository.deleteById(id);
    }
}
