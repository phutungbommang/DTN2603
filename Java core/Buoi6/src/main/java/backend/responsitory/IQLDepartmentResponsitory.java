package backend.responsitory;

import entity.Account;
import entity.Department;

import java.util.List;

public interface IQLDepartmentResponsitory {
    List<Department> getDepartments();
    boolean suaDepartmentNameTheoId(Integer departmentId,String departmentName);
    boolean xoaDepartmentTheoId(Integer departmentId);
    boolean themDepartment(String departmentName);
    boolean isDepartmentExist(int id);
    void themListDepartment(List<Department> departments);
}
