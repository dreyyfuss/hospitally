package com.hospitally.hospitally.service;

import com.google.gson.Gson;
import com.hospitally.hospitally.dto.request.EquipmentCreateRequest;
import com.hospitally.hospitally.dto.request.EquipmentUpdateRequest;
import com.hospitally.hospitally.model.entity.Equipment;
import com.hospitally.hospitally.repository.database.interfaces.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService
{
    public final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository)
    {
        this.equipmentRepository = equipmentRepository;
    }

    public int createEquipemnt(EquipmentCreateRequest request)
    {
        Gson gson = new Gson();
        var equipment = gson.fromJson(gson.toJson(request), com.hospitally.hospitally.model.entity.Equipment.class);
        System.out.println("EquipmentId: "+ equipment);
        System.out.println("EquipmentName: "+ equipment);
        return equipmentRepository.createEquipment(equipment);
    }

    public List<Equipment> getAllEquipments()
    {
        return equipmentRepository.getAllEquipments();
    }

    public Equipment getEquipmentById(Long id)
    {
        return equipmentRepository.getEquipmentById(id);
    }

    public boolean updateEquipment(Long equipmentId, EquipmentUpdateRequest request)
    {
        boolean updated = equipmentRepository.updateEquipment(equipmentId,request);
        return true;
    }

    public boolean deleteEquipment(Long id)
    {
        boolean deleted = equipmentRepository.deleteEquipment(id);
        return true;
    }
}
