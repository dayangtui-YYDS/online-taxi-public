package com.ycc.apipassenger.controller;

import com.ycc.internalcommon.dto.ResponseResult;
import com.ycc.internalcommon.response.NumberCodeResponse;
import com.ycc.internalcommon.response.TokenResponse;
import com.ycc.internalcommon.request.VerificationCodeDTO;
import com.ycc.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: VerificationCodeController
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-20 11:44
 */
@RestController
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult<NumberCodeResponse> verificationCode(@RequestParam("phone") String phone){
        return verificationCodeService.generateVerificationCode(phone);
    }

    @PostMapping("/verification-code-check")
    public ResponseResult<TokenResponse> checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        System.out.println(verificationCodeDTO);
        return verificationCodeService.checkVerificationCode(verificationCodeDTO.getPassengerPhone(),
                verificationCodeDTO.getVerificationCode());
    }
}