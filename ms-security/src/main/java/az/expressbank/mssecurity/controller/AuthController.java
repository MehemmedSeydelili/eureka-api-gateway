package az.expressbank.mssecurity.controller;

import az.expressbank.mssecurity.data.dto.AuthRequestDTO;
import az.expressbank.mssecurity.data.dto.JwtResponseDTO;
import az.expressbank.mssecurity.data.entity.UserInfo;
import az.expressbank.mssecurity.data.entity.UserRole;
import az.expressbank.mssecurity.data.repository.UserRepository;
import az.expressbank.mssecurity.service.AuthService;
import az.expressbank.mssecurity.service.JwtService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/api/v1/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));

        if (authentication.isAuthenticated()) {
            UserInfo user = userRepository.findByUsername(authRequestDTO.getUsername());
            List<String> roles = user.getRoles().stream()
                    .map(UserRole::getName)
                    .collect(Collectors.toList());

            return JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername(), roles))
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @GetMapping("/ping")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/check/token")
    boolean isTokenValid(@NonNull @RequestParam String token) {
        return authService.isTokenValid(token);
    }

    @GetMapping("/check/role")
    boolean checkRole(@RequestParam String token, @RequestParam String requestPath) {
        return authService.checkRole(token, requestPath);
    }

    @GetMapping("/get/claims")
    List<String> getAllRoles(@NonNull @RequestParam String token) {
        return authService.getClaims(token);
    }
}
