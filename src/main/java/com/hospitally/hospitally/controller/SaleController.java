package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.SaleCreateRequest;
import com.hospitally.hospitally.dto.request.SaleUpdateRequest;
import com.hospitally.hospitally.model.entity.Sale;
import com.hospitally.hospitally.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sale")
public class SaleController
{
    public final SaleService saleService;

    public SaleController(SaleService saleService)
    {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<String> createSale (@RequestBody SaleCreateRequest request)
    {
        saleService.createSale(request);
        return ResponseEntity.ok("Sale created successfully");
    }

    @GetMapping
    public List<Sale> getAllSales()
    {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Long id)
    {
        return saleService.getSaleById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSale(@PathVariable Long id, @RequestBody SaleUpdateRequest request)
    {
        boolean updated = saleService.updateSale(id,request);
        return updated ? ResponseEntity.ok("Sale updated successfully") : ResponseEntity.badRequest().body("Invalid Input");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long id)
    {
        boolean deleted = saleService.deleteSale(id);
        return deleted ? ResponseEntity.ok("Sale deleted successfully") : ResponseEntity.badRequest().body("Invalid Input");
    }
}
