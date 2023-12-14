package az.expressbank.mssecurity.controller;

import az.expressbank.mssecurity.data.dto.AuthRequestDTO;
import az.expressbank.mssecurity.data.dto.JwtResponseDTO;
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

@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthService authService;

    @PostMapping("/api/v1/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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
