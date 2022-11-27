package com.ycc.internalcommon.dto;

import com.ycc.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: ResponseResult
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-21 13:59
 */
@Data
@Accessors(chain = true)
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    public static ResponseResult success(){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getMessage());
    }

    public static <T> ResponseResult<T> success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).
                setMessage(CommonStatusEnum.SUCCESS.getMessage()).setData(data);
    }

    public static ResponseResult fail(int code, String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    public static <T> ResponseResult<T> fail(int code, String message, T data){
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
}