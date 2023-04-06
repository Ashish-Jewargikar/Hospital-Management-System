package com.example.HospitalManagemetSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DoctorPatientDTO {
    private int doctorId;
    private String doctorName;
    private int patientId;
    private String patientName;
}
