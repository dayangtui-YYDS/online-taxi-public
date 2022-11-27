package com.ycc.internalcommon.request;

import lombok.Data;

/**
 * @ClassName: VerificationCodeDTO
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-20 11:46
 */
@Data
public class VerificationCodeDTO {
    private String passengerPhone;
    private String verificationCode;

    @Override
    public String toString() {
        return "VerificationCodeDTO{" +
                "passengerPhone='" + passengerPhone + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}