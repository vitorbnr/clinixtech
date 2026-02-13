package com.vitorbnr.clinixtech.service;

import com.vitorbnr.clinixtech.dto.appointment.AppointmentDetailDTO;
import com.vitorbnr.clinixtech.dto.appointment.CreateAppointmentDTO;
import com.vitorbnr.clinixtech.model.Appointment;
import com.vitorbnr.clinixtech.model.enums.AppointmentStatus;
import com.vitorbnr.clinixtech.repository.AppointmentRepository;
import com.vitorbnr.clinixtech.repository.DoctorRepository;
import com.vitorbnr.clinixtech.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public AppointmentDetailDTO schedule(CreateAppointmentDTO data) {
        var doctor = doctorRepository.findById(data.doctorId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        var patient = patientRepository.findById(data.patientId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        if (data.startTime().isAfter(data.endTime())) {
            throw new RuntimeException("A data final deve ser maior que a inicial");
        }

        var conflicts = appointmentRepository.findConflictingAppointments(
                doctor.getId(),
                data.startTime(),
                data.endTime()
        );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Médico indisponível neste horário. Conflito com outro agendamento.");
        }

        var appointment = Appointment.builder()
                .doctor(doctor)
                .patient(patient)
                .startTime(data.startTime())
                .endTime(data.endTime())
                .status(AppointmentStatus.SCHEDULED)
                .build();

        appointmentRepository.save(appointment);

        return new AppointmentDetailDTO(appointment);
    }
}