package com.example.HospitalManagemetSystem.Repository;

import com.example.HospitalManagemetSystem.Entity.Doctor;
import com.example.HospitalManagemetSystem.Entity.Hospital;
import com.example.HospitalManagemetSystem.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    int countByHospital(Hospital hospital);
    //List<Patient> findByDoctor(Doctor doctor);
}