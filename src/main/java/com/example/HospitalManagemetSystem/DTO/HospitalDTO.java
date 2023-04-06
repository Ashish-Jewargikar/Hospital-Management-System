package com.example.HospitalManagemetSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalDTO {
    private int id;
    private String name;
    private int noOfPatients;
    private List<DoctorDTO> doctorList;
}
