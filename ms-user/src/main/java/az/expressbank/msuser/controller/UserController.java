package az.expressbank.msuser.controller;

import az.expressbank.msuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final Environment environment;

    private final UserService userService;

    @GetMapping("/port")
    public String sayUser(){
        return "Port number: " + environment.getProperty("local.server.port");
    }

    @GetMapping("/accounts")
    public String getUserAccounts() {
        return userService.getUserAccount();
    }
}
