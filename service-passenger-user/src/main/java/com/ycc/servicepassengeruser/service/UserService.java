package com.ycc.servicepassengeruser.service;

import com.ycc.internalcommon.dto.ResponseResult;
import com.ycc.servicepassengeruser.dto.PassengerUser;
import com.ycc.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserService
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-24 12:15
 */
@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrRegister(String passengerPhone){
        System.out.println("userservice: loginOrRegister");

        //根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> users = passengerUserMapper.selectByMap(map);
        // 用户不存在 插入用户
        if (users.size() == 0) {
            PassengerUser user = new PassengerUser();
            user.setPassengerName("张三");
            user.setPassengerGender((byte)0);
            user.setPassengerPhone(passengerPhone);
            user.setState((byte)0);
            LocalDateTime now = LocalDateTime.now();
            user.setGmtCreate(now);
            user.setGmtModified(now);
            passengerUserMapper.insert(user);
        }

        return ResponseResult.success();
    }
}