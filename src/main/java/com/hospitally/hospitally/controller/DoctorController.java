package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.doctor.CreateDoctorRequest;
import com.hospitally.hospitally.dto.request.doctor.UpdateDoctorRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.doctor.DoctorResponse;
import com.hospitally.hospitally.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    public final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DoctorResponse>> createDoctor (@RequestBody CreateDoctorRequest request) {
        ApiResponse<DoctorResponse> response = doctorService.createDoctor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorResponse>> getDoctorById (@PathVariable int id) {
        ApiResponse<DoctorResponse> response = doctorService.getDoctorById(id);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND
        ).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<DoctorResponse>>> getAllDoctors () {
        ApiResponse<List<DoctorResponse>> response = doctorService.getAllDoctors();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorResponse>> updateDoctor (
            @PathVariable int id, @RequestBody UpdateDoctorRequest request) {
        ApiResponse<DoctorResponse> response = doctorService.updateDoctor(id, request);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        ).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<DoctorResponse>> deleteDoctor (@PathVariable int id) {
        ApiResponse<DoctorResponse> response = doctorService.deleteDoctor(id);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        ).body(response);
    }
}
