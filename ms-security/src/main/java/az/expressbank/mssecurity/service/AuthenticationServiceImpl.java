package az.expressbank.mssecurity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl{

    /*private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserInfo signUp(SignUpRequest signUpRequest){
        UserInfo userInfo = new UserInfo();

        userInfo.setEmail(signUpRequest.getEmail());
        userInfo.setName(signUpRequest.getFirstName());
        userInfo.setSecondName(signUpRequest.getLastName());
        userInfo.setRole(UserRole.USER);
        userInfo.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(userInfo);
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
    }*/
}

