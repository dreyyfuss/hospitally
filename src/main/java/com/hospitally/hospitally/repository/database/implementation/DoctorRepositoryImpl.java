package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.dto.request.doctor.UpdateDoctorRequest;
import com.hospitally.hospitally.model.entity.Doctor;
import com.hospitally.hospitally.repository.database.interfaces.DoctorRepository;
import com.hospitally.hospitally.repository.database.query.DoctorQuery;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DoctorRepositoryImpl implements DoctorRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DoctorRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createDoctor(Doctor doctor) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("staffId", doctor.getDoctorStaffId())
                .addValue("specialization", doctor.getDoctorSpecialization());

        return jdbcTemplate.update(DoctorQuery.INSERT_DOCTOR, params);
    }

    @Override
    public Optional<Doctor> findDoctorById(int doctorId) {
        MapSqlParameterSource params = new MapSqlParameterSource("doctorId", doctorId);

        try {
            List<Doctor> doctors = jdbcTemplate.query(
                    DoctorQuery.FIND_DOCTOR_BY_ID, params, doctorRowMapper()
            );
            return doctors.stream().findFirst();
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    @Override
    public List<Doctor> findAllDoctors() {
        return jdbcTemplate.query(DoctorQuery.FIND_ALL_DOCTOR, doctorRowMapper());
    }

    @Override
    public int updateDoctor(int doctorId, UpdateDoctorRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("doctorId", doctorId)
                .addValue("specialization", request.getSpecialization())
                .addValue("status", request.getStatus());

        return jdbcTemplate.update(DoctorQuery.UPDATE_DOCTOR, params);
    }

    @Override
    public int deleteDoctor(int doctorId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("doctorId", doctorId);

        return jdbcTemplate.update(DoctorQuery.DELETE_DOCTOR, params);
    }

    private RowMapper<Doctor> doctorRowMapper() {
        return (rs, rowNum) -> Doctor.builder()
                .doctorId(rs.getInt("doctorId"))
                .doctorStaffId(rs.getInt("doctorStaffId"))
                .doctorSpecialization(rs.getString("doctorSpecialization"))
                .doctorStatus(rs.getString("doctorStatus"))
                .doctorCreatedAt(rs.getTimestamp("doctorCreatedAt").toLocalDateTime())
                .doctorUpdatedAt(rs.getTimestamp("doctorUpdatedAt").toLocalDateTime())
                .build();
    }
}
