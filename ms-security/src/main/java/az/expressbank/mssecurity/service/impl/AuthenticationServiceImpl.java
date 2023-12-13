package az.expressbank.mssecurity.service.impl;

import az.expressbank.mssecurity.data.dto.JwtAuthenticationResponse;
import az.expressbank.mssecurity.data.dto.SignInRequest;
import az.expressbank.mssecurity.data.dto.SignUpRequest;
import az.expressbank.mssecurity.data.entity.Role;
import az.expressbank.mssecurity.data.entity.User;
import az.expressbank.mssecurity.data.repository.UserRepository;
import az.expressbank.mssecurity.service.AuthenticationService;
import az.expressbank.mssecurity.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public User signUp(SignUpRequest signUpRequest){
        User user = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getFirstName());
        user.setSecondName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest signInRequest){
        log.info("In signin metod!!!!!!!!!!!!!!!!!");
        try{
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                    signInRequest.getPassword()));
            System.out.println(authenticate.getPrincipal());

        }
        catch (Exception e){
            log.error("Error catch during authenticatpe rocess!!!!!!!!!"+e.getMessage());
            throw new RuntimeException("!!!!!!!!!!!!!!!!!!!!");
        }

        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException("Invalid email or password"));
        log.info("userRepository.findByEmail() method worked");
        var jwt = jwtService.generateToken(user);
        log.info("jwtService.generateToken(user) method worked");
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        log.info("jwtService.generateRefreshToken() method worked");
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        log.info("setter methods worked");

        return jwtAuthenticationResponse;
    }
}

