package com.hospitally.hospitally.service;

import com.google.gson.Gson;
import com.hospitally.hospitally.dto.request.DepartmentCreateRequest;
import com.hospitally.hospitally.dto.request.DepartmentUpdateRequest;
import com.hospitally.hospitally.model.entity.Department;
import com.hospitally.hospitally.repository.database.interfaces.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService
{
    public final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository)
    {
        this.departmentRepository = departmentRepository;
    }

    public int createDepartment(DepartmentCreateRequest request)
    {
        Gson gson = new Gson();
        var department = gson.fromJson(gson.toJson(request), Department.class);
        System.out.println("DepartmentId: "+ department);
        System.out.println("DepartmentName: "+ department);
        return departmentRepository.createDepartment(department);
    }

    public List<Department> getAllDepartments()
    {
        return departmentRepository.getAllDepartments();
    }

    public Department getDepartmentById(Long id)
    {
        return departmentRepository.getDepartmentById(id);
    }

    public boolean updateDepartment(Long departmentId, DepartmentUpdateRequest request)
    {
        boolean updated = departmentRepository.updateDepartment(departmentId,request);
        return true;
    }

    public boolean deleteDepartment(Long id)
    {
        boolean deleted = departmentRepository.deleteDepartment(id);
        return true;
    }
}
