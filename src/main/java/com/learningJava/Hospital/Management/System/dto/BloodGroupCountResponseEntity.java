package com.learningJava.Hospital.Management.System.dto;

import com.learningJava.Hospital.Management.System.entity.type.BloodGroupType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BloodGroupCountResponseEntity {
    private BloodGroupType bloodGroupType;
    private Long count;
}
