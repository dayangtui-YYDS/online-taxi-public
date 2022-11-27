package com.ycc.apipassenger.controller;

import com.ycc.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-27 16:09
 */
@RestController
public class TestController {

    @GetMapping("/authTest")
    public ResponseResult<String> authTest(){
        return ResponseResult.success("auth test");
    }

    @GetMapping("noAuthTest")
    public ResponseResult<String> noAuthTest(){
        return ResponseResult.success("no auth test");
    }
}