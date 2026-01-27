package com.learningJava.Hospital.Management.System.service;

import org.springframework.stereotype.Service;

import com.learningJava.Hospital.Management.System.entity.Appointment;
import com.learningJava.Hospital.Management.System.entity.Doctor;
import com.learningJava.Hospital.Management.System.entity.Patient;
import com.learningJava.Hospital.Management.System.repository.AppointmentRepository;
import com.learningJava.Hospital.Management.System.repository.DoctorRepository;
import com.learningJava.Hospital.Management.System.repository.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment,Long doctorId, Long patientId) {
        // TODO Auto-generated method stub
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new RuntimeException("Doctor not found"));
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new RuntimeException("Patient not found"));

        if(appointment.getId()!=null){
            throw new RuntimeException("Appointment ID must be null for new appointment");
        }       
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);


        patient.getAppointments().add(appointment);
        doctor.getAppointments().add(appointment);
        
       return appointmentRepository.save(appointment);
    }
    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId,
                                                          Long newDoctorId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Doctor newDoctor = doctorRepository.findById(newDoctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Doctor oldDoctor = appointment.getDoctor();
        if (oldDoctor != null) {
            oldDoctor.getAppointments().remove(appointment);
        }

        newDoctor.getAppointments().add(appointment);

        return appointment;
    }
}
