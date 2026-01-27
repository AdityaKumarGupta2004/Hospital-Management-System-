package com.learningJava.Hospital.Management.System.repository;

import com.learningJava.Hospital.Management.System.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}