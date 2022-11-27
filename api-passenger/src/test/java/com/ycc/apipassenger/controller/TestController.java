package com.ycc.apipassenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-20 11:31
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test api psssenger";
    }
}