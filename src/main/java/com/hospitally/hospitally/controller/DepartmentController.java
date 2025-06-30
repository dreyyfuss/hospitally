package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.DepartmentCreateRequest;
import com.hospitally.hospitally.dto.request.DepartmentUpdateRequest;
import com.hospitally.hospitally.model.entity.Department;
import com.hospitally.hospitally.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController
{
    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService)
    {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentCreateRequest request)
    {
        departmentService.createDepartment(request);
        return ResponseEntity.ok("Department created successfully");
    }

    @GetMapping
    public List<Department> getAllDepartments()
    {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id)
    {
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable Long id, @RequestBody DepartmentUpdateRequest request)
    {
        boolean updated = departmentService.updateDepartment(id,request);
        return updated ? ResponseEntity.ok("Department updated successfully") : ResponseEntity.badRequest().body("Invalid Input");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id)
    {
        boolean deleted = departmentService.deleteDepartment(id);
        return deleted ? ResponseEntity.ok("Department deleted successfully") : ResponseEntity.badRequest().body("Invalid Input");
    }
}
