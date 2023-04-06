package com.example.HospitalManagemetSystem.Service;

import com.example.HospitalManagemetSystem.DTO.DoctorDTO;
import com.example.HospitalManagemetSystem.DTO.HospitalDTO;
import com.example.HospitalManagemetSystem.Entity.Doctor;
import com.example.HospitalManagemetSystem.Entity.Hospital;
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
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public HospitalDTO addHospital(HospitalDTO hospitalDTO) {
        Hospital hospital = new Hospital();
        hospital.setName(hospitalDTO.getName());
        hospital.setNoOfPatients(hospitalDTO.getNoOfPatients());

        List<Doctor> doctorList = new ArrayList<>();
        for (DoctorDTO doctorDTO : hospitalDTO.getDoctorList()) {
            Doctor doctor = new Doctor();
            doctor.setName(doctorDTO.getName());
            doctor.setSpeciality(doctorDTO.getSpeciality());
            doctor.setHospital(hospital);
            doctorList.add(doctor);
        }
        hospital.setDoctors(doctorList);

        Hospital savedHospital = hospitalRepository.save(hospital);

        HospitalDTO savedHospitalDTO = new HospitalDTO();
        savedHospitalDTO.setId(savedHospital.getId());
        savedHospitalDTO.setName(savedHospital.getName());
        savedHospitalDTO.setNoOfPatients(savedHospital.getNoOfPatients());

        List<DoctorDTO> savedDoctorDTOList = new ArrayList<>();
        for (Doctor doctor : savedHospital.getDoctors()) {
            DoctorDTO savedDoctorDTO = new DoctorDTO();
            savedDoctorDTO.setId(doctor.getId());
            savedDoctorDTO.setName(doctor.getName());
            savedDoctorDTO.setSpeciality(doctor.getSpeciality());
            savedDoctorDTO.setHospitalId(doctor.getHospital().getId());
            savedDoctorDTOList.add(savedDoctorDTO);
        }
        savedHospitalDTO.setDoctorList(savedDoctorDTOList);

        return savedHospitalDTO;
    }

    public int getTotalPatientsInHospital(int hospitalId) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(hospitalId);
        if (optionalHospital.isPresent()) {
            Hospital hospital = optionalHospital.get();
            return patientRepository.countByHospital(hospital);
        } else {
            throw new EntityNotFoundException("Hospital not found with ID: " + hospitalId);
        }
    }

    public int getTotalDoctorsInHospital(int hospitalId) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(hospitalId);
        if (optionalHospital.isPresent()) {
            Hospital hospital = optionalHospital.get();
            return doctorRepository.countByHospital(hospital);
        } else {
            throw new EntityNotFoundException("Hospital not found with ID: " + hospitalId);
        }
    }
}