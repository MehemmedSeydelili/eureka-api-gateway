package az.expressbank.mssecurity;

import az.expressbank.mssecurity.data.entity.UserInfo;
import az.expressbank.mssecurity.data.entity.UserRole;
import az.expressbank.mssecurity.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
@EnableDiscoveryClient
public class MsSecurityApplication implements CommandLineRunner {


    private final UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(MsSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserRole userRole=new UserRole();
        userRole.setName("ADMIN");
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);
            UserInfo user = new UserInfo();
            user.setUsername("mahammad");
            user.setPassword(new BCryptPasswordEncoder().encode("mahammad"));
            user.setRoles(roles);
            userRepository.save(user);
    }
}
