package com.learningJava.Hospital.Management.System.repository;


import com.learningJava.Hospital.Management.System.entity.User;
import com.learningJava.Hospital.Management.System.entity.type.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);


    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
}
