package az.expressbank.mssecurity.controller;

import az.expressbank.mssecurity.data.entity.UserInfo;
import az.expressbank.mssecurity.data.entity.UserRole;
import az.expressbank.mssecurity.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import oracle.net.ano.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public ResponseEntity<UserInfo> signUp(@RequestBody UserInfo user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRoles()==null) {
            UserRole userRole = new UserRole();
            userRole.setName("USER");
            user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        }
        userRepository.save(user);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/all")
    public UserInfo all(){
        return (UserInfo) userRepository.findAll();
    }


/*    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }*/

}
