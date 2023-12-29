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

        return "/api/account".contains(requestPath) && roles.contains("ADMIN");
    }
//Problemin sebebi burdadir. Rollar JWT icerisine yazilmir deye burada extract ola bilmir
}
