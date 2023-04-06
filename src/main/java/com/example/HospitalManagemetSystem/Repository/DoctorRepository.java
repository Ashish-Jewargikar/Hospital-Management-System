package com.example.HospitalManagemetSystem.Repository;

import com.example.HospitalManagemetSystem.Entity.Doctor;
import com.example.HospitalManagemetSystem.Entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    int countByHospital(Hospital hospital);
}
