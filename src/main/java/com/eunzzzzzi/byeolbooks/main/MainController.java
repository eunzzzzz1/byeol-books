package com.eunzzzzzi.byeolbooks.main;

import com.eunzzzzzi.byeolbooks.users.Users;
import com.eunzzzzzi.byeolbooks.users.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
@Slf4j
public class MainController {

    private final UsersService usersService;

    @GetMapping("")
    public ModelAndView main(Authentication auth) {
        ModelAndView mav = new ModelAndView();

        if(auth != null) {
            Users loginUser = usersService.getLoginUserByLoginId(auth.getName());
            if(loginUser != null) {
                mav.addObject("loginUserNickname", loginUser.getUserNickname());
            }
        }
        mav.setViewName("index");
        return mav;
    }

}
