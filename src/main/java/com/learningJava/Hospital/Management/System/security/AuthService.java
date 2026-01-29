package com.learningJava.Hospital.Management.System.security;

import com.learningJava.Hospital.Management.System.dto.LoginRequestDto;
import com.learningJava.Hospital.Management.System.dto.LoginResponseDto;
import com.learningJava.Hospital.Management.System.dto.SignUpRequestDto;
import com.learningJava.Hospital.Management.System.dto.SignupResponseDto;
import com.learningJava.Hospital.Management.System.entity.User;
import com.learningJava.Hospital.Management.System.repository.PatientRepository;
import com.learningJava.Hospital.Management.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token, user.getId());
    }

    // login controller
    public SignupResponseDto signup(SignUpRequestDto signupRequestDto) {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);

        if(user!=null) throw new IllegalArgumentException("User already Exists");

        user = userRepository.save(
                User.builder()
                        .username(signupRequestDto.getUsername())
                        .password(passwordEncoder.encode(
                                signupRequestDto.getPassword()))
//                        .role(signupRequestDto.getRole())
                        .build());
        return new SignupResponseDto(user.getId(), user.getUsername());
    }
}
