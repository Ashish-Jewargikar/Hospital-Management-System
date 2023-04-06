package com.example.HospitalManagemetSystem.DTO;

import com.example.HospitalManagemetSystem.Enum.PatientDisease;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private int id;
    private String name;
    private int age;
    private PatientDisease disease;
    private int hospitalId;
    private int doctorId;
}