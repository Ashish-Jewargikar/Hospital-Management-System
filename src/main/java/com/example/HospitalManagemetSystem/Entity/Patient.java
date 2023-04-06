package com.example.HospitalManagemetSystem.Entity;
import com.example.HospitalManagemetSystem.Enum.PatientDisease;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private PatientDisease disease;

    @ManyToOne
    @JoinColumn(name="hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
}