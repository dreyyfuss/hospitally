package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.dto.request.DepartmentCreateRequest;
import com.hospitally.hospitally.dto.request.DepartmentUpdateRequest;
import com.hospitally.hospitally.model.entity.Department;

import java.util.List;

public interface DepartmentRepository
{
    public int createDepartment(Department department);
    List<Department> getAllDepartments();
    public Department getDepartmentById(Long id);
    public boolean updateDepartment(Long departmentId, DepartmentUpdateRequest request);
    public boolean deleteDepartment( Long id);
}
