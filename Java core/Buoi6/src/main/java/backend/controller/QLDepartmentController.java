package backend.controller;

import backend.service.IQLAccountService;
import backend.service.IQLDepartmentService;
import backend.service.impl.QLAccountServiceImpl;
import backend.service.impl.QLDepartmentServiceImpl;
import entity.Account;
import entity.Department;

import java.util.List;

public class QLDepartmentController {
    private IQLDepartmentService service;

    public QLDepartmentController() {
        service = new QLDepartmentServiceImpl();
    }

    public List<Department> getDepartments() {
        List<Department> departments = service.getDepartments();
        return departments;
    }

    public boolean xoaDepartmentTheoId(Integer departmentId) {
        return service.xoaDepartmentTheoId(departmentId);
    }

    public boolean suaDepartmentNameTheoId(Integer departmentId,String departmentName) {
        return service.suaDepartmentNameTheoId(departmentId,departmentName);
    }

    public boolean themDepartment(String departmentName) {
        return service.themDepartment(departmentName);
    }
     public boolean isDepartmentExist(int id){
        return service.isDepartmentExist(id);
     }
}
