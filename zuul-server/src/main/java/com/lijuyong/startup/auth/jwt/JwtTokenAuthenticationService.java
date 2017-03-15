package com.lijuyong.startup.auth.jwt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by john on 2017/3/9.
 */
@Service
public class JwtTokenAuthenticationService {

    private final Log logger = LogFactory.getLog(this.getClass());
    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String AUTH_HEADER_TYPE="Bearer";
    public void addAuthorizationHeader(HttpServletResponse response,String token){
        response.addHeader(AUTH_HEADER_NAME,"Bearer " + token);

    }
    public String parseTokenHeader(HttpServletRequest request){
        String token = request.getHeader(AUTH_HEADER_NAME);
        int tokenShiftPos = AUTH_HEADER_TYPE.length() + 1;

        logger.info(token);
        if ( token == null || token.length()< tokenShiftPos ){
            logger.error("token is not valid");
            return null;
        }
        if ( !token.startsWith(AUTH_HEADER_TYPE)){
            logger.error("token type is not supported");
            return  null;
        }
        token = token.substring(tokenShiftPos);
        logger.info(token);
        return  token;
    }

}
