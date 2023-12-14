package az.expressbank.mssecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    public boolean isTokenValid(String token) {
        return jwtService.isTokenValid(token);
    }

    public List<String> getClaims(String token) {
        return jwtService.extractRoles(token);
    }

    public boolean checkRole(String token, String requestPath) {
        return isAuthorized(token, requestPath);
    }

    private boolean isAuthorized(String token, String requestPath) {
        List<String> roles = jwtService.extractRoles(token);

        if ("/payment/info".equals(requestPath) && roles.contains("ROLE_ADMIN")) {
            return true;
        } else if ("/payment/all/*".contains(requestPath) && (roles.contains("ROLE_ADMIN") || roles.contains("ROLE_USER"))) {
            return true;
        }
        return false;
    }

}
