package com.ycc.internalcommon.constant;

import lombok.Data;
import lombok.Getter;

public enum CommonStatusEnum {
    /**
     * 验证码错误提示范围:1000-1099
     */

    VERIFICATION_CODE_ERROR(1000,"验证码错误"),
    VERIFICATION_CODE_EXPIRE(1099,"验证码过期"),

    TOKEN_SIGN_ERROR(1100,"token签名错误"),
    TOKEN_TIMEOUT(1101,"token超时"),
    TOKEN_ALGORITHM_MISMATCH(1102,"token算法不匹配"),
    TOKEN_INVALID(1103,"token非法"),

    SUCCESS(1,"success"),
    FAIL(0,"fail");

    CommonStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private int code;
    @Getter
    private String message;
}
