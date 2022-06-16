package com.tripfestival.controller;

import com.tripfestival.domain.user.Role;
import com.tripfestival.service.TestService;
import com.tripfestival.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {

    private final TestService testService;

    private final JwtUtil jwtUtil;

    @GetMapping("/jwttest")
    public String jwtTest() {
        return jwtUtil.createToken(1L, Role.USER);
    }


    @GetMapping("/jwtverify")
    public Map jwtVerify(String jwtKey) throws UnsupportedEncodingException {
        return jwtUtil.verifyJWT(jwtKey);
    }

    @GetMapping("/read")
    public String read() {
        testService.read();
        return "ok";
    }
    @GetMapping("/write")
    public String write() {
        testService.write();
        return "ok";
    }
}