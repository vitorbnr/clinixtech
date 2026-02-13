package com.vitorbnr.clinixtech.config;

import com.vitorbnr.clinixtech.model.Doctor;
import com.vitorbnr.clinixtech.model.Patient;
import com.vitorbnr.clinixtech.model.User;
import com.vitorbnr.clinixtech.model.enums.Role;
import com.vitorbnr.clinixtech.repository.DoctorRepository;
import com.vitorbnr.clinixtech.repository.PatientRepository;
import com.vitorbnr.clinixtech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("default")
public class DevSeeder implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private DoctorRepository doctorRepository;
    @Autowired private PatientRepository patientRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) return;

        User userDoctor = User.builder()
                .fullName("Dr. House")
                .email("house@clinix.com")
                .password(passwordEncoder.encode("123456"))
                .role(Role.DOCTOR)
                .build();
        userRepository.save(userDoctor);

        Doctor doctor = Doctor.builder()
                .crm("123456-SP")
                .specialty("Diagnostico")
                .user(userDoctor)
                .build();
        doctorRepository.save(doctor);

        User userPatient = User.builder()
                .fullName("Vitor Patient")
                .email("vitor@gmail.com")
                .password(passwordEncoder.encode("123456"))
                .role(Role.PATIENT)
                .build();
        userRepository.save(userPatient);

        Patient patient = Patient.builder()
                .cpf("111.222.333-44")
                .phone("11999999999")
                .user(userPatient)
                .build();
        patientRepository.save(patient);

        System.out.println("---------------------------------------------");
        System.out.println("DADOS DE TESTE CRIADOS:");
        System.out.println("Doctor ID: " + doctor.getId());
        System.out.println("Patient ID: " + patient.getId());
        System.out.println("---------------------------------------------");
    }
}