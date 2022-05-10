package com.tripfestival.service.user;

import com.tripfestival.domain.user.Role;
import com.tripfestival.domain.user.User;
import com.tripfestival.dto.user.KakaoLoginDto;
import com.tripfestival.repository.user.UserRepository;
import com.tripfestival.util.JwtUtil;
import com.tripfestival.util.KakaoOAuth2;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import com.tripfestival.vo.user.UserKakaoLoginResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final KakaoOAuth2 kakaoOAuth2;

    private final JwtUtil jwtUtil;

    public UserKakaoLoginResponseVo kakaoLoginInsert(String code) {

        KakaoLoginDto kakaoLoginDto = kakaoOAuth2.getUserInfo(code);
        System.out.println("kakaoLoginDto.getId() = " + kakaoLoginDto.getId());

        String userId = "kakao" + kakaoLoginDto.getId();

        User user = userRepository.findByUid(userId);

        UserKakaoLoginResponseVo userKakaoLoginResponseVo = null;

        if(user == null) {
            // DB Insert
            User newUser = User.builder()
                    .uid(userId)
                    .upw(null)
                    .nickname(null)
                    .userImg(null)
                    .email(null)
                    .role(Role.USER)
                    .deleteAt(false)
                    .build();

            userRepository.save(newUser);

            // JwtKey
            String jwtKey = jwtUtil.createToken(newUser.getId(), Role.USER);

            userKakaoLoginResponseVo = new UserKakaoLoginResponseVo(Response.SUCCESS, jwtKey);
        }

        return userKakaoLoginResponseVo;
    }
}
