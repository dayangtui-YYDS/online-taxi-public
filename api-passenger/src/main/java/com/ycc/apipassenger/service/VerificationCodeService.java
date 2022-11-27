package com.ycc.apipassenger.service;

import com.ycc.apipassenger.remote.ServicePassengerUserClient;
import com.ycc.internalcommon.constant.CommonStatusEnum;
import com.ycc.internalcommon.constant.UserIdentity;
import com.ycc.internalcommon.dto.ResponseResult;
import com.ycc.internalcommon.response.NumberCodeResponse;
import com.ycc.internalcommon.response.TokenResponse;
import com.ycc.apipassenger.remote.ServiceVerificationCodeClient;
import com.ycc.internalcommon.util.JwtUtils;
import com.ycc.internalcommon.util.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: VerificationCodeService
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-20 11:50
 */
@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationCodeClient serviceVerificationCodeClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult<NumberCodeResponse> generateVerificationCode(String phone){
        System.out.println("调用验证码服务获取验证码");
        ResponseResult<NumberCodeResponse> responseResult = serviceVerificationCodeClient.getNumberCode(6);
        int numberCode = responseResult.getData().getNumberCode();
        System.out.println("remote number code:"+numberCode);

        System.out.println("存入redis");
        String key =RedisUtils.generateVerificationCodeKey(phone);
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        NumberCodeResponse numberCodeResponse = new NumberCodeResponse();
        numberCodeResponse.setNumberCode(numberCode);
        return ResponseResult.success(numberCodeResponse);
    }

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;
    public ResponseResult<TokenResponse> checkVerificationCode(String phone, String verificationCode){
        System.out.println("根据手机号从redis中读取验证码");

        String key = RedisUtils.generateVerificationCodeKey(phone);
        String _verificationCode = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis verificationCode:"+_verificationCode);

        // 验证码过期
        if (StringUtils.isBlank(_verificationCode)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_EXPIRE.getCode(),
                    CommonStatusEnum.VERIFICATION_CODE_EXPIRE.getMessage());
        }

        // 验证码错误
        if (!_verificationCode.trim().equals(verificationCode.trim())) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),
                    CommonStatusEnum.VERIFICATION_CODE_EXPIRE.getMessage());
        }

        servicePassengerUserClient.loginOrRegister(phone);

        String token = JwtUtils.generateToken(phone, UserIdentity.Passenger);

        // token存进redis
        String tokenKey = RedisUtils.generateTokenKey(phone, UserIdentity.Passenger);
        stringRedisTemplate.opsForValue().set(tokenKey, token, 30, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return ResponseResult.success(tokenResponse);
    }
}