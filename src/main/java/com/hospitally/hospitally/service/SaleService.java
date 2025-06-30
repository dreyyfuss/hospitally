package com.hospitally.hospitally.service;

import com.google.gson.Gson;
import com.hospitally.hospitally.dto.request.SaleCreateRequest;
import com.hospitally.hospitally.dto.request.SaleUpdateRequest;
import com.hospitally.hospitally.model.entity.Sale;
import com.hospitally.hospitally.repository.database.interfaces.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService
{
    public final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository)
    {
        this.saleRepository = saleRepository;
    }

    public int createSale(SaleCreateRequest request)
    {
        Gson gson = new Gson();
        var sale = gson.fromJson(gson.toJson(request), com.hospitally.hospitally.model.entity.Sale.class);
        System.out.println("SaleId: "+ sale);
        System.out.println("SalePaymentId: "+ sale);
        System.out.println("SalePatientId: "+ sale);
        return saleRepository.createSale(sale);
    }

    public List<Sale> getAllSales()
    {
        return saleRepository.getAllSales();
    }

    public Sale getSaleById(Long id)
    {
        return saleRepository.getSaleById(id);
    }

    public boolean updateSale(Long saleId, SaleUpdateRequest request )
    {
        boolean updated = saleRepository.updateSale(saleId,request);
        return true;
    }

    public boolean deleteSale(Long id)
    {
        boolean deleted = saleRepository.deleteSale(id);
        return true;
    }

}
