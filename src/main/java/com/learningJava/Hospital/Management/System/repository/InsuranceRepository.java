package com.learningJava.Hospital.Management.System.repository;

import com.learningJava.Hospital.Management.System.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}