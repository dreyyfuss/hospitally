package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.dto.request.EquipmentUpdateRequest;
import com.hospitally.hospitally.model.entity.Department;
import com.hospitally.hospitally.model.entity.Equipment;

import java.util.List;

public interface EquipmentRepository
{
    public int createEquipment(Equipment equipment);
    List<Equipment> getAllEquipments();
    public Equipment getEquipmentById(Long id);
    public boolean updateEquipment(Long equipmentId, EquipmentUpdateRequest request);
    public boolean deleteEquipment( Long id);
}
