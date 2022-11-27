package com.ycc.servicepassengeruser.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: PassengerUser
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-24 17:16
 */

@Data
@TableName("passenger_user")
public class PassengerUser {
    private Long id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private String passengerPhone;
    private String passengerName;
    private byte passengerGender;
    private byte state;
}