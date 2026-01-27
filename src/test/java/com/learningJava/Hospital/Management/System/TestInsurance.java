package com.learningJava.Hospital.Management.System;


import com.learningJava.Hospital.Management.System.entity.Appointment;
import com.learningJava.Hospital.Management.System.entity.Insurance;
import com.learningJava.Hospital.Management.System.entity.Patient;
import com.learningJava.Hospital.Management.System.service.AppointmentService;
import com.learningJava.Hospital.Management.System.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class TestInsurance {
    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    // @Test
    // public void testInsurance() {
    //     Insurance insurance = Insurance.builder()
    //             .policyNumber("HDFC_1234")
    //             .provider("HDFC")
    //             .validUntil(LocalDate.of(2030, 12, 12))
    //             .build();

    //     Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);

    //     System.out.println(patient);

    //     // var newPatient = insuranceService.disaccociateInsuranceFromPatient(patient.getId());

    //     // System.out.println(newPatient);
    // }


    @Test
    public void TestCreateAppointment() {
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 0))
                .reason("Cancer")
                .build();

        

       var newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);
// 
    //    System.out.println(newAppointment);

    //    var updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 3L);

    //    System.out.println(updatedAppointment);
    }


}
