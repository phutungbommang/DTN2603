package backend.service.impl;

import backend.responsitory.IQLAccountResponsitory;
import backend.responsitory.IQLDepartmentResponsitory;
import backend.responsitory.impl.QLAccountResponsitory;
import backend.responsitory.impl.QLDepartmentResponsitory;
import backend.service.IQLDepartmentService;
import backend.service.ImportFileCSV;
import context.AccountContext;
import context.DepartmentContext;
import entity.Account;
import entity.Department;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QLDepartmentServiceImpl implements IQLDepartmentService, ImportFileCSV<Department, DepartmentContext> {
    private IQLDepartmentResponsitory repository;
    public QLDepartmentServiceImpl() {
        repository = new QLDepartmentResponsitory();// khoi tao gia tri
    }
    @Override
    public List<Department> getDepartments() {
        List<Department> departments = repository.getDepartments();
        return departments;
    }

    @Override
    public boolean suaDepartmentNameTheoId(Integer departmentId, String departmentName) {
        return repository.suaDepartmentNameTheoId(departmentId, departmentName);
    }

    @Override
    public boolean xoaDepartmentTheoId(Integer departmentId) {

        return repository.xoaDepartmentTheoId(departmentId);
    }

    @Override
    public boolean themDepartment(String departmentName) {

        return repository.themDepartment(departmentName);
    }

    @Override
    public boolean isDepartmentExist(int id) {
        return repository.isDepartmentExist(id);
    }

    @Override
    public String importDepartmentCSV(String url) {
        List<Department> departments = repository.getDepartments();
        Set<String> setDepartmentName = departments.stream()
                .map(Department::getDepartmentName)
                .collect(Collectors.toSet());
        DepartmentContext context = new DepartmentContext(setDepartmentName);
        String pathErrorsFile = "C:\\Users\\media\\OneDrive\\Máy tính\\DTN2603\\Java core\\csv\\department_errors.csv";
        return ImportFileCSV.super.importCSV(url, context, pathErrorsFile
        );
    }

    @Override
    public void validation(String line,
                           List<Department> entities,
                           List<String> listErrors,
                           DepartmentContext context) {

        String[] values = line.split(",");

        if (values.length != 1) {
            listErrors.add(line + ",Sai số lượng cột");
            return;
        }

        List<String> errors = new ArrayList<>();

        String departmentName = values[0].trim();

        if (departmentName.length() < 2 ||
                departmentName.length() > 50) {

            errors.add("DepartmentName có độ dài từ 2 đến 50 ký tự");

        } else if (context.getSetDepartmentName().contains(departmentName)) {

            errors.add("DepartmentName đã tồn tại");
        }

        if (errors.isEmpty()) {

            Department department = new Department();

            department.setDepartmentName(departmentName);

            context.getSetDepartmentName().add(departmentName);

            entities.add(department);

        } else {

            String error = String.join(" - ", errors);

            listErrors.add(line + "," + error);
        }
    }

    @Override
    public void saveAll(List<Department> entities) {
        repository.themListDepartment(entities);
    }

    @Override
    public void exportErrors(String header, List<String> listErrors, String pathErrorFile) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathErrorFile));
            bw.write(header + "error_message");
            bw.newLine();
            for (String error : listErrors) {
                bw.write(error);
                bw.newLine();
            }
            bw.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
