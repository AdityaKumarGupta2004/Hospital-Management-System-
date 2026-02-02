package com.learningJava.Hospital.Management.System.dto;
import com.learningJava.Hospital.Management.System.entity.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private String username;
    private String password;
    private String name;
    private Set<RoleType> roles = new HashSet<>();

}