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
import org.springframework.web.bind.annotation.RequestParam;
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

    // 아이디 중복확인
    @RequestMapping("/idchecking")
    public int idChecking(@RequestParam("userId") String userId) {
        // 있으면 1 없으면 0
        System.out.println("아작스 통신 중...");
        return usersService.idDuplicationCheck(userId)?1:0;
    }

    // 회원가입 진행
    @PostMapping("/signup")
    public ModelAndView signUp(@Valid UsersAddForm usersAddForm, BindingResult bindingResult) {

        ModelAndView mav = new ModelAndView();
        // 유효하지 않은 데이터를 입력하고 submit 했을 시, alert와 함께 회원가입 폼을 다시 띄운다.
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
