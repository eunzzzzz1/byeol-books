package com.eunzzzzzi.byeolbooks.users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView signUp(@Valid UsersAddForm usersAddForm, BindingResult bindingResult) {

        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()) {
            ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
            String errorMsg = objectError.getDefaultMessage();
            log.error("[회원가입 Form] " + errorMsg);
            mav.setViewName("sign/signup");
            mav.addObject("errorMsg", errorMsg);
            return mav;
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

        mav.setViewName("redirect:/");
        return mav;
    }

    // 로그인 폼
    @RequestMapping("/signin")
    public String signIn() {
        return "sign/signin";
    }

}
