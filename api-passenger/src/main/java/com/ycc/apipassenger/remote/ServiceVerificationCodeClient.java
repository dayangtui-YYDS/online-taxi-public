package com.ycc.apipassenger.remote;

import com.ycc.internalcommon.dto.ResponseResult;
import com.ycc.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: ServiceVerificationCodeClient
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-21 14:56
 */
@FeignClient("service-verificationcode")
public interface ServiceVerificationCodeClient {
    @RequestMapping(method = RequestMethod.GET, value = "/numberCode/{size}")
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size") int size);
}