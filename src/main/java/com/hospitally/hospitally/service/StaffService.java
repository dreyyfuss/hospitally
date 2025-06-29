package com.hospitally.hospitally.service;

import com.google.gson.Gson;
import com.hospitally.hospitally.dto.request.StaffCreateRequest;
import com.hospitally.hospitally.dto.request.StaffUpdateRequest;
import com.hospitally.hospitally.exceptions.staffExceptions.FailedUpdateException;
import com.hospitally.hospitally.exceptions.staffExceptions.InvalidDataInputException;
import com.hospitally.hospitally.model.entity.Staff;
import com.hospitally.hospitally.repository.database.interfaces.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService
{
    private final StaffRepository staffRepository;

        public StaffService(StaffRepository staffRepository)
        {
            this.staffRepository = staffRepository;
        }

    public int createStaffMember(StaffCreateRequest request)
    {
        Gson gson = new Gson();

        if (request.getStaffDepartmentId() == null|| request.getStaffRole() == null ||
                request.getStaffGender() == null || request.getStaffDateOfBirth() == null)
        {
            throw new InvalidDataInputException("Field is required");
        }

        var staff = gson.fromJson(gson.toJson(request), Staff.class);
        System.out.println("StaffDepartmentId: "+ staff);
        System.out.println("StaffUserId: "+ staff);
        System.out.println("StaffRole: "+ staff);
        System.out.println("StaffGender: "+ staff);
        System.out.println("staffDateOfBirth: "+ staff);

        return staffRepository.createStaffMember(staff);
    }


    public List<Staff> getAllMembers()
    {
        if (staffRepository.getAllStaffMembers().isEmpty())
        {
            System.out.println("No staff members found");
        }
        return staffRepository.getAllStaffMembers();
    }

    public Staff getStaffMemberById(Long id)
    {
        return staffRepository.getStaffMemberById(id);
    }

    public boolean updateStaffMember(Long staffId, StaffUpdateRequest request)
    {
        boolean updated = staffRepository.updateStaffMember(staffId,request);
        if (!updated)
        {
            throw new FailedUpdateException("Failed to update staff member");
        }
        return true;
    }

    public boolean deleteStaffMember(Long id)
    {
        if (id == null)
        {
            throw new InvalidDataInputException("Staff ID is required for deletion");
        }

        boolean deleted = staffRepository.deleteStaffMember(id);

        if (!deleted)
        {
            throw new FailedUpdateException("Failed to delete staff member with ID: " + id);
        }

        return true;
    }








}