package az.expressbank.mssecurity.service.impl;

import az.expressbank.mssecurity.data.dto.SignUpRequest;
import az.expressbank.mssecurity.data.entity.Role;
import az.expressbank.mssecurity.data.entity.User;
import az.expressbank.mssecurity.data.repository.UserRepository;
import az.expressbank.mssecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signUp(SignUpRequest signUpRequest){
        User user = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getFirstName());
        user.setSecondName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }

}
