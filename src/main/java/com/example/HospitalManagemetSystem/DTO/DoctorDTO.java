package com.example.HospitalManagemetSystem.DTO;

import com.example.HospitalManagemetSystem.Enum.DoctorSpeciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private int id;
    private String name;
    private DoctorSpeciality speciality;
    private int hospitalId;
}
