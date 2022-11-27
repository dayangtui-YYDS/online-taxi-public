package com.ycc.servicepassengeruser.controller;

import com.ycc.internalcommon.dto.ResponseResult;
import com.ycc.internalcommon.request.VerificationCodeDTO;
import com.ycc.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: UserController
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-24 11:37
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    ResponseResult<String> loginOrRegister(@RequestParam("phone") String phone){
        userService.loginOrRegister(phone);
        return ResponseResult.success();
    }
}