package com.ycc.apipassenger.remote;

import com.ycc.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: ServicePassengerUserClient
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-24 19:10
 */
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {
    @PostMapping("/user")
    ResponseResult loginOrRegister(@RequestParam("phone") String phone);
}
