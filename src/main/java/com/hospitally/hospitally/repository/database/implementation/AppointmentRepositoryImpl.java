package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.dto.request.AppointmentCreateRequest;
import com.hospitally.hospitally.dto.request.AppointmentUpdateRequest;
import com.hospitally.hospitally.model.entity.Appointment;
import com.hospitally.hospitally.repository.database.interfaces.AppointmentRepository;
import com.hospitally.hospitally.repository.database.query.AppointmentQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository
{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AppointmentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int createAppointment(Appointment appointment)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentPatientId", appointment.getAppointmentPatientId())
                .addValue("appointmentDate", appointment.getAppointmentDate())
                .addValue("appointmentTime",appointment.getAppointmentTime())
                .addValue("appointmentDepartmentId", appointment.getAppointmentDepartmentId())
                .addValue("appointmentDoctorId", appointment.getAppointmentDoctorId());

        return jdbcTemplate.update(AppointmentQuery.CREATE_APPOINTMENT,params);
    }

    @Override
    public Appointment getAppointmentById(Long id)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", id);
        return jdbcTemplate.queryForObject(AppointmentQuery.GET_APPOINTMENT_BY_ID,params,new BeanPropertyRowMapper<>(Appointment.class));
    }

    @Override
    public List<Appointment> getAllAppointments()
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(AppointmentQuery.GET_ALL_APPOINTMENTS,params,new BeanPropertyRowMapper<>(Appointment.class));
    }

    @Override
    public boolean updateAppointment(Long appointmentId, AppointmentUpdateRequest request)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentDiagnosis", request.getAppointmentDiagnosis())
                .addValue("appointmentDate",request.getAppointmentDate())
                .addValue("appointmentTime",request.getAppointmentTime())
                .addValue("appointmentStatus", request.getAppointmentStatus())
                .addValue("appointmentPaymentId",request.getAppointmentPaymentId())
                .addValue("appointmentPatientId",request.getAppointmentPaymentId())
                .addValue("appointmentDoctorId", request.getAppointmentDoctorId())
                .addValue("appointmentDepartmentId", request.getAppointmentDepartmentId());

        return jdbcTemplate.update(AppointmentQuery.UPDATE_APPOINTMENT,params) > 0;
    }

    @Override
    public boolean deleteAppointment(Long id)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId",id);
        return jdbcTemplate.update(AppointmentQuery.DELETE_APPOINTMENT,params) > 0;
    }
}
