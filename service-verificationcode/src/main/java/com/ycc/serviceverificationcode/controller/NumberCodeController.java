package com.ycc.serviceverificationcode.controller;

import com.ycc.internalcommon.dto.ResponseResult;
import com.ycc.internalcommon.response.NumberCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: NumberCodeController
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-21 12:08
 */
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult<NumberCodeResponse> numberCode(@PathVariable("size") int size){
        System.out.println("size:"+size);

        // 生成验证码
        int numberCode = (int) ((Math.random()*9 +1)*Math.pow(10,size-1));
        System.out.println("generate src numberCode:"+numberCode);
        NumberCodeResponse numberCodeResponse = new NumberCodeResponse();
        numberCodeResponse.setNumberCode(numberCode);
        return ResponseResult.success(numberCodeResponse);
    }
}