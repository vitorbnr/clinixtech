package com.vitorbnr.clinixtech.controller;

import com.vitorbnr.clinixtech.dto.appointment.CreateAppointmentDTO;
import com.vitorbnr.clinixtech.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping
    public ResponseEntity schedule(@RequestBody @Valid CreateAppointmentDTO data) {
        var dto = service.schedule(data);
        return ResponseEntity.ok(dto);
    }
}