package com.example.HospitalManagemetSystem.Controller;

import com.example.HospitalManagemetSystem.DTO.DoctorDTO;

import com.example.HospitalManagemetSystem.DTO.PatientDTO;
import com.example.HospitalManagemetSystem.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/addDoctor")
    public DoctorDTO addDoctor(@RequestBody DoctorDTO doctor){
        return doctorService.addDoctor(doctor);
    }

    @GetMapping("/doctors/{doctorId}/patients")
    public ResponseEntity<List<PatientDTO>> getPatientsByDoctorId(@PathVariable int doctorId) {
        List<PatientDTO> patients = doctorService.getPatients(doctorId);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
