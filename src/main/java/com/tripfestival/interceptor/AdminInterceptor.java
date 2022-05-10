package com.tripfestival.interceptor;

import com.tripfestival.domain.user.Role;
import com.tripfestival.domain.user.User;
import com.tripfestival.exception.JwtVerifyFailException;
import com.tripfestival.repository.user.UserRepository;
import com.tripfestival.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        JwtUtil jwtUtil = new JwtUtil();

        Enumeration headerNames = request.getHeaderNames();

        boolean authorizationCheck = false;

        Long uId = null;

        while (headerNames.hasMoreElements()) {
            String name = (String) headerNames.nextElement();
            String value = request.getHeader(name);

            if (name.equals("Authorization") || name.equals("authorization")) {
                authorizationCheck = true;
                if (value.equals("null")) {
                    log.error("UserAuthInterceptor - JwtVerifyFailException: value equals null");
                    throw new JwtVerifyFailException();
                }

                Map<String, Object> returnValue = jwtUtil.verifyJWT(value);

                uId = Long.valueOf(String.valueOf(returnValue.get("user_id")));


                // No ReturnValue
                if (returnValue == null) {
                    log.error("UserAuthInterceptor - JwtVerifyFailException: returnValue equals null");
                    throw new JwtVerifyFailException();
                }
            }
        }

        // No Authorization
        if (authorizationCheck == false) {
            log.error("UserAuthInterceptor - JwtVerifyFailException: authorization null");
            throw new JwtVerifyFailException();
        }

        User user = userRepository.findById(uId).get();

        if (user.getRole() == Role.ADMIN) {
            return true;
        } else {
            return false;
        }
    }
}
