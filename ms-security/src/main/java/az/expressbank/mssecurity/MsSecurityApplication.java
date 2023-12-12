package az.expressbank.mssecurity;

import az.expressbank.mssecurity.data.entity.Role;
import az.expressbank.mssecurity.data.entity.User;
import az.expressbank.mssecurity.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
public class MsSecurityApplication {


    private final UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(MsSecurityApplication.class, args);
    }

   /* @Override
    public void run(String... args) throws Exception {
        Optional<Role> admin = userRepository.findByRole(Role.ADMIN);
        if (admin.isEmpty()){
            User user = new User();
            user.setName("mahammad");
            user.setSecondName("seydalili");
            user.setEmail("mm@gmail.com");
            user.setPassword(new BCryptPasswordEncoder().encode("mahammad1"));
            user.setRole(Role.ADMIN);

            userRepository.save(user);
        }
    }*/
}
