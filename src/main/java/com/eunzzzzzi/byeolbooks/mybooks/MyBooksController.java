package com.eunzzzzzi.byeolbooks.mybooks;

import com.eunzzzzzi.byeolbooks.users.Users;
import com.eunzzzzzi.byeolbooks.users.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mybooks")
@Slf4j
public class MyBooksController {

    private final UsersService usersService;

    @GetMapping("")
    public ModelAndView booksMain(@RequestParam(value="id") String id) {
        ModelAndView mav = new ModelAndView();

        if(id.isEmpty()) {
            mav.setViewName("redirect:/user/signin");
            return mav;
        }
        mav.addObject("loginUserId", id);
        mav.addObject("loginUser", usersService.getLoginUserByLoginId(id));
        mav.setViewName("mybooks/main");
        return mav;
    }

}
