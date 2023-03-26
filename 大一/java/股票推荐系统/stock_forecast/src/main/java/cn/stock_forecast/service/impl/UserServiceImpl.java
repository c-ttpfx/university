package cn.stock_forecast.service.impl;

import cn.stock_forecast.domain.User;
import cn.stock_forecast.mapper.UserMapper;
import cn.stock_forecast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @Author: 程梁
 * @Date: 2021/12/07/1:08
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean login(User user) {
        User user1 = null;
        try {
            user1 = userMapper.login(user);
            System.out.println("查询到的user:"+user1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (user1==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean register(User user) {
        int row = 0;
        try {
            row = userMapper.register(user);
        } catch (Exception e) {
            return false;
        }
        if (row > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int getIdByUserNameAndPassword(String username, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        int uid = userMapper.getIdByUserNameAndPassword(map);
        return uid;
    }
}
