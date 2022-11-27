package com.ycc.internalcommon.util;

import com.ycc.internalcommon.constant.UserIdentity;

/**
 * @ClassName: RedisUtils
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-27 19:17
 */
public class RedisUtils {
    private static final String verificationCodePrefix = "passenger-verification-code";
    private static final String tokenPrefix = "token";

    /**
     * 根据手机号生成验证码Key
     * @param phone
     * @return
     */
    public static String generateVerificationCodeKey(String phone){
        return String.format("%s-%s",verificationCodePrefix,phone);
    }

    /**
     * 生成token key
     * @param phone
     * @param userIdentity
     * @return
     */
    public static String generateTokenKey(String phone, UserIdentity userIdentity){
        return String.format("%s-%s-%s", tokenPrefix, phone, userIdentity.toString());
    }
}