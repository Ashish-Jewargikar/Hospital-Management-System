package com.example.HospitalManagemetSystem.Controller;

import com.example.HospitalManagemetSystem.DTO.PatientDTO;
import com.example.HospitalManagemetSystem.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    PatientService patientService;
    @PostMapping("/addPatient")
    public String addPatient(@RequestBody PatientDTO patient){
        return patientService.addPatient(patient);
    }
}
