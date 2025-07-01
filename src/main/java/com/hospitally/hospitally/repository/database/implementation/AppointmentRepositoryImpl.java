package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.dto.request.appointment.UpdateAppointmentRequest;
import com.hospitally.hospitally.model.entity.Appointment;
import com.hospitally.hospitally.repository.database.interfaces.AppointmentRepository;
import com.hospitally.hospitally.repository.database.query.AppointmentQuery;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AppointmentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createAppointment(Appointment appointment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientId", appointment.getAppointmentPatientId())
                .addValue("doctorId", appointment.getAppointmentDoctorId())
                .addValue("departmentId", appointment.getAppointmentDepartmentId())
                .addValue("paymentId", appointment.getAppointmentPaymentId())
                .addValue("date", appointment.getAppointmentDate())
                .addValue("time", appointment.getAppointmentTime())
                .addValue("diagnosis", appointment.getAppointmentDiagnosis());
        return jdbcTemplate.update(AppointmentQuery.INSERT_APPOINTMENT, params);
    }

    @Override
    public Optional<Appointment> findAppointmentById(int id) {
        List<Appointment> results = jdbcTemplate.query(
                AppointmentQuery.FIND_APPOINTMENT_BY_ID,
                new MapSqlParameterSource("id", id),
                rowMapper()
        );
        return results.stream().findFirst();
    }

    @Override
    public List<Appointment> findAllAppointments() {
        return jdbcTemplate.query(AppointmentQuery.FIND_ALL_APPOINTMENTS, rowMapper());
    }

    @Override
    public int updateAppointment(int id, UpdateAppointmentRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("date", request.getAppointmentDate())
                .addValue("time", request.getAppointmentTime())
                .addValue("diagnosis", request.getAppointmentDiagnosis())
                .addValue("status", request.getAppointmentStatus());
        return jdbcTemplate.update(AppointmentQuery.UPDATE_APPOINTMENT, params);
    }

    @Override
    public int deleteAppointment(int id) {
        return jdbcTemplate.update(AppointmentQuery.DELETE_APPOINTMENT, new MapSqlParameterSource("id", id));
    }

    private RowMapper<Appointment> rowMapper() {
        return (rs, rowNum) -> Appointment.builder()
                .appointmentId(rs.getInt("appointmentId"))
                .appointmentPatientId(rs.getInt("appointmentPatientId"))
                .appointmentDoctorId(rs.getInt("appointmentDoctorId"))
                .appointmentDepartmentId(rs.getInt("appointmentDepartmentId"))
                .appointmentPaymentId(rs.getInt("appointmentPaymentId"))
                .appointmentDate(rs.getDate("appointmentDate").toLocalDate())
                .appointmentTime(rs.getTime("appointmentTime").toLocalTime())
                .appointmentDiagnosis(rs.getString("appointmentDiagnosis"))
                .appointmentStatus(rs.getString("appointmentStatus"))
                .appointmentCreatedAt(rs.getTimestamp("appointmentCreatedAt").toLocalDateTime())
                .appointmentUpdatedAt(rs.getTimestamp("appointmentUpdatedAt").toLocalDateTime())
                .build();
    }
}
