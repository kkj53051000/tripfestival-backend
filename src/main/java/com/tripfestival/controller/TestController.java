package com.tripfestival.controller;

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
    private final JwtUtil jwtUtil;

    @GetMapping("/jwttest")
    public String jwtTest() {
        return jwtUtil.createToken(1L);
    }


    @GetMapping("/jwtverify")
    public Map jwtVerify(String jwtKey) throws UnsupportedEncodingException {
        return jwtUtil.verifyJWT(jwtKey);
    }
}