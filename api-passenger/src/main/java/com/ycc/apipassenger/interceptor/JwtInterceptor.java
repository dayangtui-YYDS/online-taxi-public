package com.ycc.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ycc.internalcommon.constant.CommonStatusEnum;
import com.ycc.internalcommon.dto.ResponseResult;
import com.ycc.internalcommon.dto.TokenResult;
import com.ycc.internalcommon.util.JwtUtils;
import com.ycc.internalcommon.util.RedisUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName: JwtInterceptor
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-26 21:46
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        PrintWriter printWriter = response.getWriter();
        TokenResult tokenResult;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            printWriter.println(JSONObject.fromObject(ResponseResult.fail(CommonStatusEnum.TOKEN_SIGN_ERROR.getCode(),
                    CommonStatusEnum.TOKEN_SIGN_ERROR.getMessage())).toString());
            return false;
        } catch (TokenExpiredException e){
            printWriter.println(JSONObject.fromObject(ResponseResult.fail(CommonStatusEnum.TOKEN_TIMEOUT.getCode(),
                    CommonStatusEnum.TOKEN_TIMEOUT.getMessage())).toString());
            return false;
        }catch (AlgorithmMismatchException e){
            printWriter.println(JSONObject.fromObject(ResponseResult.fail(CommonStatusEnum.TOKEN_ALGORITHM_MISMATCH.getCode(),
                    CommonStatusEnum.TOKEN_ALGORITHM_MISMATCH.getMessage())).toString());
            return false;
        }catch (Exception e){
            printWriter.println(JSONObject.fromObject(ResponseResult.fail(CommonStatusEnum.TOKEN_INVALID.getCode(),
                    CommonStatusEnum.TOKEN_INVALID.getMessage())).toString());
            return false;
        }

        if (tokenResult == null) {
            printWriter.println(JSONObject.fromObject(ResponseResult.fail(CommonStatusEnum.TOKEN_INVALID.getCode(),
                    CommonStatusEnum.TOKEN_INVALID.getMessage())));
            return false;
        }

        String key = RedisUtils.generateTokenKey(tokenResult.getPhone(), tokenResult.getIdentity());
        String redisToken = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(redisToken) || !token.trim().equals(redisToken.trim())) {
            printWriter.println(JSONObject.fromObject(ResponseResult.fail(CommonStatusEnum.TOKEN_INVALID.getCode(),
                    CommonStatusEnum.TOKEN_INVALID.getMessage())));
            return false;
        }

        return true;
    }
}