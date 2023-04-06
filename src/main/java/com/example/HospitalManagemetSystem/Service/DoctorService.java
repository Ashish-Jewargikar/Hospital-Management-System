package com.example.HospitalManagemetSystem.Service;

import com.example.HospitalManagemetSystem.DTO.DoctorDTO;
import com.example.HospitalManagemetSystem.DTO.DoctorPatientDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    public DoctorDTO addDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setSpeciality(doctorDTO.getSpeciality());

        Optional<Hospital> optionalHospital = hospitalRepository.findById(doctorDTO.getHospitalId());
        if (optionalHospital.isPresent()) {
            doctor.setHospital(optionalHospital.get());
        } else {
            throw new EntityNotFoundException("Hospital not found with ID: " + doctorDTO.getHospitalId());
        }

        Doctor savedDoctor = doctorRepository.save(doctor);
        DoctorDTO savedDoctorDTO = new DoctorDTO();
        savedDoctorDTO.setId(savedDoctor.getId());
        savedDoctorDTO.setName(savedDoctor.getName());
        //savedDoctorDTO.setSpecialization(savedDoctor.getSpecialization());
        savedDoctorDTO.setSpeciality(savedDoctorDTO.getSpeciality());
        savedDoctorDTO.setHospitalId(savedDoctor.getHospital().getId());
        return savedDoctorDTO;
    }

    public List<PatientDTO> getPatients(int doctorId) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            List<Patient> patients = doctor.getPatients();
            List<PatientDTO> patientDTOs = new ArrayList<>();
            for (Patient patient : patients) {
                PatientDTO patientDTO = new PatientDTO();
                patientDTO.setId(patient.getId());
                patientDTO.setName(patient.getName());
                patientDTO.setAge(patient.getAge());
                patientDTO.setDisease(patient.getDisease());
                patientDTO.setHospitalId(patient.getHospital().getId());
                patientDTO.setDoctorId(doctor.getId());
                patientDTOs.add(patientDTO);
            }
            return patientDTOs;
        } else {
            throw new EntityNotFoundException("Doctor not found with ID: " + doctorId);
        }
    }

}
