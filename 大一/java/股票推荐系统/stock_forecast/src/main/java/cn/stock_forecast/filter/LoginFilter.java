package cn.stock_forecast.filter;
import cn.stock_forecast.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: 程梁
 * @Date: 2021/11/25/19:08
 * @Description: 为人所不为, 能人所不能
 */
public class LoginFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username==null){
            //没有登录
            System.out.println("拦截了");
            response.sendRedirect(request.getContextPath()+"/login.html");
            return false;
        }
        //放行  访问目标资源
        System.out.println("拦截放行");
        return true;
    }

}
