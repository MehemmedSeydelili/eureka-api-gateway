package az.expressbank.msuser.controller;

import az.expressbank.msuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Environment environment;

    @Autowired
    private UserService userService;

    public UserController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping
    public String sayUser(){
        return "Port number: " + environment.getProperty("local.server.port");
    }

    @GetMapping("/accounts")
    public String getUserAccounts() {
        return userService.getUserAccount();
    }
}
