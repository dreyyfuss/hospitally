package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.EquipmentCreateRequest;
import com.hospitally.hospitally.dto.request.EquipmentUpdateRequest;
import com.hospitally.hospitally.model.entity.Equipment;
import com.hospitally.hospitally.service.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/api/v1/equipment")
public class EquipmentController
{
    public final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService)
    {
        this.equipmentService = equipmentService;
    }

    @PostMapping
    public ResponseEntity<String> createEquipment(@RequestBody EquipmentCreateRequest request)
    {
        equipmentService.createEquipemnt(request);
        return ResponseEntity.ok("Equipment created successfully");
    }

    @GetMapping
    public List<Equipment> getAllEquipments()
    {
        return equipmentService.getAllEquipments();
    }

    @GetMapping("/{id}")
    public Equipment getEquipmentById(@PathVariable Long id)
    {
        return equipmentService.getEquipmentById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEquipment(@PathVariable Long id, @RequestBody EquipmentUpdateRequest request)
    {
        boolean updated = equipmentService.updateEquipment(id,request);
        return updated ? ResponseEntity.ok("Equipment updated successfully") : ResponseEntity.badRequest().body("Invalid Input");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEquipment(@PathVariable Long id)
    {
        boolean deleted = equipmentService.deleteEquipment(id);
        return deleted ? ResponseEntity.ok("Equipment deleted successfully") : ResponseEntity.badRequest().body("Invalid Input");
    }
}
