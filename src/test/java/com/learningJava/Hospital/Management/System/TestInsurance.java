package com.learningJava.Hospital.Management.System;

import com.learningJava.Hospital.Management.System.entity.Appointment;
import com.learningJava.Hospital.Management.System.entity.Insurance;
import com.learningJava.Hospital.Management.System.entity.Patient;
import com.learningJava.Hospital.Management.System.repository.PatientRepository;
import com.learningJava.Hospital.Management.System.service.AppointmentService;
import com.learningJava.Hospital.Management.System.service.InsuranceService;
import com.learningJava.Hospital.Management.System.service.PatientService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestInsurance {
    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    // @Autowired
    // private PatientService patientService;
    //
    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testInsurance() {
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234535")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030, 12, 12))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 3L);

        System.out.println(patient);
        var assignedInsurance = patient.getInsurance();
        System.out.println(assignedInsurance);
        var assignedPatient = assignedInsurance.getPatient();
        System.out.println(assignedPatient);

        var newPatient = insuranceService.disaccociateInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);

        // var newPatient =
        // insuranceService.disaccociateInsuranceFromPatient(patient.getId());

        // System.out.println(newPatient);
    }

    @Test
    public void TestCreateAppointment() {
//        List<Appointment> appointments = new ArrayList<>();
//
//        for (int i = 0; i < 3; i++) {
//            Appointment appointment = Appointment.builder()
//                    .appointmentTime(LocalDateTime.of(2025, 11, 1, 14 + i, 0))
//                    .reason("Cancer")
//                    .build();
//
//            appointments.add(
//                    appointmentService.createNewAppointment(appointment, 3L, 6L));
//        }
//
//        var patient = appointments.get(0).getPatient();
//        System.out.println("Appointments for patient: " + patient.getName());

      patientRepository.deleteById(6L);
        // System.out.println(pat);




        // var updatedAppointment =
        // appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(),
        // 2L);

        // System.out.println(updatedAppointment);

    }

}
