package com.tripfestival.controller.user;

import com.tripfestival.service.user.UserService;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/user/kakaoLogin")
    public ResponseVo kakaoLogin(@RequestParam String code) {
        return userService.kakaoInsert(code);
    }
}
