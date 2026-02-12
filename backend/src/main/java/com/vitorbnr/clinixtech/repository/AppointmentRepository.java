package com.vitorbnr.clinixtech.repository;

import com.vitorbnr.clinixtech.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId " +
            "AND a.status <> 'CANCELED' " +
            "AND (a.startTime < :endTime AND a.endTime > :startTime)")
    List<Appointment> findConflictingAppointments(
            @Param("doctorId") UUID doctorId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}