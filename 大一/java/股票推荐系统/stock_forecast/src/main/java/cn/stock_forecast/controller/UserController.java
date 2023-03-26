package cn.stock_forecast.controller;

import cn.stock_forecast.domain.User;
import cn.stock_forecast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.support.ObjectNameManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author: 程梁
 * @Date: 2021/12/04/15:39
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(User user,HttpServletRequest request) throws IOException {
        System.out.println(user);
        int uid = userService.getIdByUserNameAndPassword(user.getUsername(), user.getPassword());
        request.getSession().setAttribute("uid",uid);
        request.getSession().setAttribute("username",user.getUsername());
        request.getSession().setAttribute("password",user.getPassword());
        boolean flag = userService.login(user);
        if (flag){
            return "succeed";
        }else {
            return "fail";
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(User user){
        System.out.println(user);
        boolean flag = userService.register(user);
        if (flag){
            return "succeed";
        }else {
            return "fail";
        }
    }

    @RequestMapping("/exit")
    public void exit(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("uid");
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("password");
        try {
            response.sendRedirect(request.getContextPath()+"/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
