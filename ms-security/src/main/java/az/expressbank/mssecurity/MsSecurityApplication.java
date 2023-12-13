package az.expressbank.mssecurity;

import az.expressbank.mssecurity.data.entity.UserInfo;
import az.expressbank.mssecurity.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
@RequiredArgsConstructor
public class MsSecurityApplication implements CommandLineRunner {


    private final UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(MsSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

            UserInfo user = new UserInfo();
            user.setUsername("mahammad");
            user.setPassword(new BCryptPasswordEncoder().encode("mahammad"));
            userRepository.save(user);

    }
}
