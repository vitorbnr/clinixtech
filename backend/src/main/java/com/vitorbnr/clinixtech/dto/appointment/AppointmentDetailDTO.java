package com.vitorbnr.clinixtech.dto.appointment;

import com.vitorbnr.clinixtech.model.Appointment;
import com.vitorbnr.clinixtech.model.enums.AppointmentStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentDetailDTO(
        UUID id,
        UUID doctorId,
        String doctorName,
        UUID patientId,
        String patientName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        AppointmentStatus status
) {
    public AppointmentDetailDTO(Appointment appointment) {
        this(
                appointment.getId(),
                appointment.getDoctor().getId(),
                appointment.getDoctor().getUser().getFullName(),
                appointment.getPatient().getId(),
                appointment.getPatient().getUser().getFullName(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getStatus()
        );
    }
}