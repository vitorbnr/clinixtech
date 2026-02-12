package com.vitorbnr.clinixtech.repository;

import com.vitorbnr.clinixtech.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Optional<Doctor> findByCrm(String crm);
}