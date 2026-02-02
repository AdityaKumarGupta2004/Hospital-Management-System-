package com.learningJava.Hospital.Management.System.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class OnboardDoctorRequestDto {
    private Long userId;
    private String specialization;
    private String name;

}