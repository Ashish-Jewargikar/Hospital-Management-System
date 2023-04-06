package com.example.HospitalManagemetSystem.Service;

import com.example.HospitalManagemetSystem.DTO.PatientDTO;
import com.example.HospitalManagemetSystem.Entity.Doctor;
import com.example.HospitalManagemetSystem.Entity.Hospital;
import com.example.HospitalManagemetSystem.Entity.Patient;
import com.example.HospitalManagemetSystem.Repository.DoctorRepository;
import com.example.HospitalManagemetSystem.Repository.HospitalRepository;
import com.example.HospitalManagemetSystem.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    public String addPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setAge(patientDTO.getAge());
        //patient.setAge(Optional.ofNullable(patientDTO.getAge()).orElse(0));

        patient.setDisease(patientDTO.getDisease());

        Optional<Hospital> optionalHospital = hospitalRepository.findById(patientDTO.getHospitalId());
        if (optionalHospital.isPresent()) {
            patient.setHospital(optionalHospital.get());
        } else {
            throw new EntityNotFoundException("Hospital not found with ID: " + patientDTO.getHospitalId());
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById(patientDTO.getDoctorId());
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            patient.setDoctor(doctor);
        } else {
            throw new EntityNotFoundException("Doctor not found with ID: " + patientDTO.getDoctorId());
        }

        Patient savedPatient = patientRepository.save(patient);
        PatientDTO savedPatientDTO = new PatientDTO();
        savedPatientDTO.setId(savedPatient.getId());
        savedPatientDTO.setName(savedPatient.getName());
        savedPatientDTO.setAge(savedPatientDTO.getAge());
        savedPatientDTO.setDisease(savedPatient.getDisease());
        savedPatientDTO.setHospitalId(savedPatient.getHospital().getId());
        savedPatientDTO.setDoctorId(savedPatient.getDoctor().getId());
        return "patient added";
    }

}

