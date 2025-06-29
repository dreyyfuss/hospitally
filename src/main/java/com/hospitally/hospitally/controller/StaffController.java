package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.StaffCreateRequest;
import com.hospitally.hospitally.dto.request.StaffUpdateRequest;
import com.hospitally.hospitally.exceptions.staffExceptions.FailedUpdateException;
import com.hospitally.hospitally.exceptions.staffExceptions.FaliedDeleteException;
import com.hospitally.hospitally.exceptions.staffExceptions.InvalidDataInputException;
import com.hospitally.hospitally.model.entity.Staff;
import com.hospitally.hospitally.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController
{
    private final StaffService staffService;

    public StaffController(StaffService staffService)
    {
        this.staffService = staffService;
    }

    @PostMapping
    public ResponseEntity<String> createStaffMember(@RequestBody StaffCreateRequest request )
    {
        staffService.createStaffMember(request);

        try
        {
            staffService.createStaffMember(request);
            return ResponseEntity.ok("Staff created successfully");
        }
        catch (InvalidDataInputException e)
        {
            return ResponseEntity.badRequest().body("Invalid Input"+e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("An error occurred while creating a staff member");
        }

    }

    @GetMapping
    public List<Staff> getAllStaffMembers()
    {
        try
        {
            staffService.getAllMembers();
            return staffService.getAllMembers();
        }
        catch  (Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }

        return staffService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Staff getStaffMemberById(@PathVariable Long id)
    {
        return staffService.getStaffMemberById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateStaffMember(@PathVariable Long id, @RequestBody StaffUpdateRequest request)
    {
        boolean updated = staffService.updateStaffMember(id,request);
       return updated ? ResponseEntity.ok("Staff updated successfully") : ResponseEntity.badRequest().body("Invalid Input");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaffMember(@PathVariable Long id)
    {
        try
        {
            boolean deleted = staffService.deleteStaffMember(id);
            if (deleted)
            {
                return ResponseEntity.ok("Staff deleted successfully");
            }
        }
        catch (FaliedDeleteException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed: " + e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("An error occurred while deleting a staff member");
        }
        return ResponseEntity.badRequest().body("Invalid Input");
    }






}
