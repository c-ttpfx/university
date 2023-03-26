package cn.stock_forecast.mapper;

import cn.stock_forecast.domain.User;

import java.util.HashMap;

/**
 * @Author: 程梁
 * @Date: 2021/12/07/0:51
 */
public interface UserMapper {

    public User login(User user);

    public int register(User user);

    public int getIdByUserNameAndPassword(HashMap<String,String> map);

}
