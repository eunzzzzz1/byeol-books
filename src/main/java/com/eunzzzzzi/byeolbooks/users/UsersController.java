package com.eunzzzzzi.byeolbooks.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/signup")
    public String signUp() {
        return "sign/signup";
    }

}
