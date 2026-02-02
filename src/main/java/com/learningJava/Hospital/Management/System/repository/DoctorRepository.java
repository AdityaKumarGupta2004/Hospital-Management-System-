package com.learningJava.Hospital.Management.System.repository;

import com.learningJava.Hospital.Management.System.entity.Doctor;
import com.learningJava.Hospital.Management.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByid(Long id);

    boolean existsByUser(User user);
}