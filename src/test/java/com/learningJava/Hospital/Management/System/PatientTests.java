package com.learningJava.Hospital.Management.System;

import com.learningJava.Hospital.Management.System.dto.BloodGroupCountResponseEntity;
import com.learningJava.Hospital.Management.System.entity.Patient;
import com.learningJava.Hospital.Management.System.repository.PatientRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatientrepository() {
        // List<Patient> patientList = patientRepository.findAll();

        // System.out.println(patientRepository.findByBirthDateOrEmail(LocalDate.of(1988,10,20),"aarav.sharma@example.com"));

        // List<Object[]> bg = patientRepository.countEachBloodGroupType();
        // for(Object[] o : bg){
        // System.out.println(o[0]+" "+o[1]);
        // }
        // List<BloodGroupCountResponseEntity> bg =
        // patientRepository.countEachBloodGroupType();
        // for(BloodGroupCountResponseEntity o : bg){
        // System.out.println(o);
        // }

        Page<Patient> patients = patientRepository.findAllPatients(PageRequest.of(0, 2, Sort.by("name").ascending()));

        for (Patient p : patients) {
            System.out.println(p);
        }

        // System.out.println(patientRepository.findAllPatients());
        // System.out.println(patientRepository.updateNameWithId("Aditya", 1L));
    }
}
