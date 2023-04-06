package com.example.HospitalManagemetSystem.Controller;

import com.example.HospitalManagemetSystem.DTO.DoctorPatientDTO;
import com.example.HospitalManagemetSystem.DTO.HospitalDTO;
import com.example.HospitalManagemetSystem.Entity.Hospital;
import com.example.HospitalManagemetSystem.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {
    @Autowired
    HospitalService hospitalService;

    @PostMapping("/addHospital")
    public HospitalDTO addhospital(@RequestBody HospitalDTO hospital){
        return hospitalService.addHospital(hospital);
    }

    @GetMapping("/{hospitalId}/patients/count")
    public ResponseEntity<Long> countPatientsInHospital(@PathVariable int hospitalId) {
        long count = hospitalService.getTotalPatientsInHospital(hospitalId);
        return ResponseEntity.ok(count);
    }
    @GetMapping("/{hospitalId}/doctors/count")
    public ResponseEntity<Long> countDoctorsInHospital(@PathVariable int hospitalId) {
        long count = hospitalService.getTotalDoctorsInHospital(hospitalId);
        return ResponseEntity.ok(count);
    }
}
