package com.eunzzzzzi.byeolbooks.users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
@Slf4j
public class UsersController {

    private final UsersService usersService;

    // 회원가입 폼
    @GetMapping("/signup")
    public String signUp(UsersAddForm usersAddForm) {
        return "sign/signup";
    }

    // 회원가입 진행
    @PostMapping("/signup")
    public String signUp(@Valid UsersAddForm usersAddForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("회원가입 에러");
            return "sign/signup";
        }

        usersService.addUser(usersAddForm.getUser_id(),
                usersAddForm.getUser_password(),
                usersAddForm.getUser_nickname(),
                usersAddForm.getUser_email(),
                Integer.parseInt(usersAddForm.getUser_sex()),
                usersAddForm.getPostcode(),
                usersAddForm.getUser_addr1(),
                usersAddForm.getUser_addr2(),
                usersAddForm.getUser_addr3(),
                "nothing",
                null);

        return "redirect:/";
    }

    // 로그인 폼
    @RequestMapping("/signin")
    public String signIn() {
        return "sign/signin";
    }

}
