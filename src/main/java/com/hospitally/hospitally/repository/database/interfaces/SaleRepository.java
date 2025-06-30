package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.dto.request.SaleCreateRequest;
import com.hospitally.hospitally.dto.request.SaleUpdateRequest;
import com.hospitally.hospitally.model.entity.Sale;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SaleRepository
{
    public int createSale(Sale sale);
    List<Sale> getAllSales();
    public Sale getSaleById(Long id);
    public boolean updateSale(Long saleId, SaleUpdateRequest request);
    public boolean deleteSale( Long id);


}
