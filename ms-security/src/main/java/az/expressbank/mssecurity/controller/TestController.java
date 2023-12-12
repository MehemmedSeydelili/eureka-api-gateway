package az.expressbank.mssecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/home")
    public String home(){
        return "Wellcome Home Page";
    }

    @GetMapping("/login")
    public String login(){
        return "Login sucesfful";
    }

    @GetMapping("/user")
    public String user(){
        return "Users area";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admins area";
    }
}
