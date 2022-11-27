package com.ycc.internalcommon.dto;

import com.ycc.internalcommon.constant.UserIdentity;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName: TokenResult
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-27 15:50
 */
@Data
@AllArgsConstructor
public class TokenResult {
    private String phone;
    private UserIdentity identity;

    @Override
    public String toString() {
        return "TokenResult{" +
                "phone='" + phone + '\'' +
                ", identity=" + identity +
                '}';
    }
}