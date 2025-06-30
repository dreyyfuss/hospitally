package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.dto.request.medicationsale.UpdateMedicationSaleRequest;
import com.hospitally.hospitally.model.entity.MedicationSale;

import java.util.List;
import java.util.Optional;

public interface MedicationSaleRepository {

    int createMedicationSale(MedicationSale medicationSale);

    Optional<MedicationSale> findMedicationSaleById(int medicationSaleId);

    List<MedicationSale> findAllMedicationSales();

    int updateMedicationSale(int medicationSaleId, UpdateMedicationSaleRequest request);

    int deleteMedicationSale(int medicationSaleId);
}
