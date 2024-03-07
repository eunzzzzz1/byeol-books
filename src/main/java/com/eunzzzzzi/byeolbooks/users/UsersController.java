package com.eunzzzzzi.byeolbooks.users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
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
    @ResponseBody
    public int idChecking(@RequestParam("userId") String userId) {
        // 있으면 1 없으면 0
        log.info("아작스 통신 - 아이디 체크 중..." + usersService.idDuplicationCheck(userId));
        return usersService.idDuplicationCheck(userId)!=null?1:0;
    }

    // 이메일 중복확인
    @RequestMapping("/emailchecking")
    @ResponseBody
    public int emailChecking(@RequestParam("userEmail") String userEmail) {
        // 있으면 1 없으면 0
        log.info("아작스 통신 - " + userEmail + " 이메일 체크 중..." + usersService.emailDuplicationCheck(userEmail));
        return usersService.emailDuplicationCheck(userEmail)?1:0;
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
    @GetMapping("/signin")
    public String signIn() {
        return "sign/signin";
    }

    // 회원 정보 페이지
    @GetMapping("/info")
    public ModelAndView userInfo(Authentication auth) {
        ModelAndView mav = new ModelAndView();

        if(auth != null) {
            Users loginUser = usersService.getLoginUserByLoginId(auth.getName());
            if(loginUser != null) {
                mav.addObject("users", loginUser);
            }
        } else {
            // 에러 페이지 나중에 추가하기
        }
        mav.setViewName("user/info");
        return mav;
    }

}
