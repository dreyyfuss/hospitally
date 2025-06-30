package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.dto.request.StaffUpdateRequest;
import com.hospitally.hospitally.model.entity.Staff;

import java.util.List;

public interface StaffRepository
{
    public int createStaffMember(Staff staff);
    List<Staff> getAllStaffMembers();
    public Staff getStaffMemberById(Long id);
    public boolean updateStaffMember(Long staffId,StaffUpdateRequest request);
    public boolean deleteStaffMember( Long id);

}
