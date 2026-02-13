package com.vitorbnr.clinixtech.dto.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateAppointmentDTO(
        @NotNull UUID doctorId,
        @NotNull UUID patientId,
        @NotNull @Future LocalDateTime startTime,
        @NotNull @Future LocalDateTime endTime
) {}