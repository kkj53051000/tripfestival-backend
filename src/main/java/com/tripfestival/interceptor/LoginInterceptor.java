package com.tripfestival.interceptor;

import com.tripfestival.domain.user.Role;
import com.tripfestival.exception.JwtVerifyFailException;
import com.tripfestival.repository.user.UserRepository;
import com.tripfestival.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;


public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

        JwtUtil jwtUtil = new JwtUtil();

        Enumeration headerNames = req.getHeaderNames();

        Long userId = 0L;

        boolean authorizationCheck = false;

        while (headerNames.hasMoreElements()) {
            String name = (String) headerNames.nextElement();
            String value = req.getHeader(name);

            if (name.equals("Authorization") || name.equals("authorization")) {
                System.out.println("들어옴!!!!!!!!!!!!!!!!!!");
                System.out.println(value);
                authorizationCheck = true;
                if (value.equals("null")) {
                    throw new JwtVerifyFailException();
                }

                Map<String, Object> returnValue = jwtUtil.verifyJWT(value);

                System.out.println("returnValue : " + returnValue);

                userId = Long.valueOf(String.valueOf(returnValue.get("userId")));

                System.out.println(userId);

                if (returnValue == null) {
                    throw new JwtVerifyFailException();
                }
            }

        }

        // No Authorization
        if (!authorizationCheck) {
            throw new JwtVerifyFailException();
        }

        req.setAttribute("userId", userId);

        return true;
    }
}
