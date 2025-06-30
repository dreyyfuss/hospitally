package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.dto.request.doctor.UpdateDoctorRequest;
import com.hospitally.hospitally.model.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {
    int createDoctor(Doctor doctor);
    Optional<Doctor> findDoctorById(int doctorId);
    List<Doctor> findAllDoctors();
    int updateDoctor(int doctorId, UpdateDoctorRequest request);
    int deleteDoctor(int doctorId);
}
