package com.ycc.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ycc.internalcommon.constant.UserIdentity;
import com.ycc.internalcommon.dto.TokenResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: JwtUtils
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-25 20:55
 */
public class JwtUtils {
    private static final String SIGN = "YCC@@@";
    private static final String JWT_KEY = "phone";
    private static final String USER_IDENTITY = "user_identity";

    /**
     * 生成token
     * @return
     */
    public static String generateToken(String phone, UserIdentity userIdentity){
        // token有效期1天

//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE,1);
//        Date date = calendar.getTime();
        JWTCreator.Builder builder = JWT.create();
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY, phone);
        map.put(USER_IDENTITY, userIdentity.toString());
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
//        builder.withExpiresAt(date);
        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static TokenResult parseToken(String token){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = decodedJWT.getClaim(JWT_KEY).toString();
        UserIdentity userIdentity = UserIdentity.valueOf(decodedJWT.getClaim(USER_IDENTITY).toString());
        return new TokenResult(phone,userIdentity);
    }
}