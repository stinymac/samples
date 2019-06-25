/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
 */

package org.mac.sample.spring.boot.web.controller;

import org.mac.sample.spring.boot.data.model.entity.User;
import org.mac.sample.spring.boot.web.constant.SessionKey;
import org.mac.sample.spring.boot.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录
 *
 * @auther mac
 * @date 2018-11-21
 */
@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> model, HttpSession session){

        if(StringUtils.hasText(username) && StringUtils.hasText(password)) {
            User user = userService.getUserBy(username);
            if (user == null) {
                model.put("msg","用户名或密码错误");
                return "login";
            }

            if (!password.equals(user.getPassword())) {
                model.put("msg","用户名或密码错误");
                return "login";
            }

            session.setAttribute(SessionKey.SESSION_USER_KEY,user);
            return "redirect:/main.html";
        }

        model.put("msg","用户名或密码错误");
        return "login";
    }
}
