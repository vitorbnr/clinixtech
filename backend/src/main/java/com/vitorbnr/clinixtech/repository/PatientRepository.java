package com.vitorbnr.clinixtech.repository;

import com.vitorbnr.clinixtech.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findByCpf(String cpf);
}