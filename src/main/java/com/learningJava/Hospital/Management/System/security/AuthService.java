package com.learningJava.Hospital.Management.System.security;

import com.learningJava.Hospital.Management.System.dto.LoginRequestDto;
import com.learningJava.Hospital.Management.System.dto.LoginResponseDto;
import com.learningJava.Hospital.Management.System.dto.SignUpRequestDto;
import com.learningJava.Hospital.Management.System.dto.SignupResponseDto;
import com.learningJava.Hospital.Management.System.entity.Patient;
import com.learningJava.Hospital.Management.System.entity.User;
import com.learningJava.Hospital.Management.System.entity.type.AuthProviderType;
import com.learningJava.Hospital.Management.System.entity.type.RoleType;
import com.learningJava.Hospital.Management.System.repository.PatientRepository;
import com.learningJava.Hospital.Management.System.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final AuthenticationManager authenticationManager;
        private final AuthUtil authUtil;
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final PatientRepository patientRepository;

        /*
         * =======================
         * EMAIL / PASSWORD LOGIN
         * =======================
         */
        public LoginResponseDto login(LoginRequestDto loginRequestDto) {

                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                loginRequestDto.getUsername(),
                                                loginRequestDto.getPassword()));

                // IMPORTANT: principal is NOT your entity
                String username = authentication.getName();

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                String token = authUtil.generateAccessToken(user);

                return new LoginResponseDto(token, user.getId());
        }

        /*
         * =======================
         * EMAIL SIGNUP
         * =======================
         */
        public SignupResponseDto signup(SignUpRequestDto signupRequestDto) {
                User user = signUpInternal(
                                signupRequestDto,
                                AuthProviderType.EMAIL,
                                null);
                return new SignupResponseDto(user.getId(), user.getUsername(),user.getName());
        }

        /*
         * =======================
         * COMMON SIGNUP LOGIC
         * =======================
         */
        public User signUpInternal(
                        SignUpRequestDto signupRequestDto,
                        AuthProviderType authProviderType,
                        String providerId) {
                User existingUser = userRepository.findByUsername(signupRequestDto.getUsername())
                                .orElse(null);

                if (existingUser != null) {
                        throw new IllegalArgumentException("User already exists");
                }
                Set<RoleType> roles = signupRequestDto.getRoles();

                if (roles == null || roles.isEmpty()) {
                        roles = EnumSet.of(RoleType.PATIENT);
                }

                User user = User.builder()
                                .username(signupRequestDto.getUsername())
                                .providerType(authProviderType)
                                .providerId(providerId)
                                .roles(signupRequestDto.getRoles())
                                .name(signupRequestDto.getName())
                                .build();

                if (authProviderType == AuthProviderType.EMAIL) {
                        user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
                }

                user = userRepository.save(user);

                Patient patient = Patient.builder()
                                .name(signupRequestDto.getName())
                                .email(signupRequestDto.getUsername())
                                .user(user)
                                .build();

                patientRepository.save(patient);

                return user;
        }

        /*
         * =======================
         * OAUTH2 LOGIN HANDLER
         * =======================
         */
        @Transactional
        public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(
                        OAuth2User oAuth2User,
                        String registrationId) {

                AuthProviderType providerType = authUtil.getProviderTypeFromRegistrationId(registrationId);

                String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);

                String email = oAuth2User.getAttribute("email");
                String name = oAuth2User.getAttribute("name");

                User user = userRepository.findByProviderIdAndProviderType(providerId, providerType)
                                .orElse(null);

                User emailUser = email == null ? null : userRepository.findByUsername(email).orElse(null);

                if (user == null && emailUser == null) {
                        // First-time OAuth signup
                        String username = authUtil.determineUsernameFromOAuth2User(
                                        oAuth2User, registrationId, providerId);

                        user = signUpInternal(
                                        new SignUpRequestDto(
                                                        username,
                                                        null,
                                                        name,
                                                        Set.of(RoleType.PATIENT)),
                                        providerType,
                                        providerId);

                } else if (user != null) {
                        // Existing OAuth user → update email if needed
                        if (email != null && !email.equals(user.getUsername())) {
                                user.setUsername(email);
                                userRepository.save(user);
                        }

                } else {
                        throw new BadCredentialsException(
                                        "This email is already registered with provider " +
                                                        emailUser.getProviderType());
                }

                String token = authUtil.generateAccessToken(user);
                return ResponseEntity.ok(new LoginResponseDto(token, user.getId()));
        }
}
