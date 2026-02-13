package com.vitorbnr.clinixtech.service;

import com.vitorbnr.clinixtech.dto.appointment.CreateAppointmentDTO;
import com.vitorbnr.clinixtech.model.Appointment;
import com.vitorbnr.clinixtech.model.Doctor;
import com.vitorbnr.clinixtech.model.Patient;
import com.vitorbnr.clinixtech.model.User;
import com.vitorbnr.clinixtech.repository.AppointmentRepository;
import com.vitorbnr.clinixtech.repository.DoctorRepository;
import com.vitorbnr.clinixtech.repository.PatientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Test
    @DisplayName("Deve impedir agendamento se médico já estiver ocupado")
    void shouldBlockDuplicateAppointment() {
        var doctorId = UUID.randomUUID();
        var patientId = UUID.randomUUID();
        var dataHora = LocalDateTime.now().plusDays(1);

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(Doctor.builder().id(doctorId).user(User.builder().fullName("Dr. House").build()).build()));
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(Patient.builder().id(patientId).user(User.builder().fullName("Vitor").build()).build()));

        when(appointmentRepository.findConflictingAppointments(any(), any(), any()))
                .thenReturn(List.of(new Appointment()));

        var dto = new CreateAppointmentDTO(doctorId, patientId, dataHora, dataHora.plusHours(1));

        assertThrows(RuntimeException.class, () -> {
            appointmentService.schedule(dto);
        });
    }
}