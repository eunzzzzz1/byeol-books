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

    // 회원가입 폼
    @GetMapping("/signup")
    public String signUp() {
        return "sign/signup";
    }

    // 로그인 폼
    @RequestMapping("/signin")
    public String signIn() {
        return "sign/signin";
    }

}
