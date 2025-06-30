package com.hospitally.hospitally.dto.request.prescription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePrescriptionRequest {
    private String comment;
    private String status;
}
