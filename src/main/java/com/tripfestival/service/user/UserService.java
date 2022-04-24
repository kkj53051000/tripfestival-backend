package com.tripfestival.service.user;

import com.tripfestival.domain.user.User;
import com.tripfestival.dto.user.KakaoLoginDto;
import com.tripfestival.repository.user.UserRepository;
import com.tripfestival.util.KakaoOAuth2;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final KakaoOAuth2 kakaoOAuth2;

    public ResponseVo kakaoInsert(String code) {

        KakaoLoginDto kakaoLoginDto = kakaoOAuth2.getUserInfo(code);
        System.out.println("kakaoLoginDto.getId() = " + kakaoLoginDto.getId());

        String userId = "kakao" + kakaoLoginDto.getId();

        User user = userRepository.findByUid(userId);


        if(user == null) {
            User newUser = User.builder()
                    .uid(userId)
                    .upw(null)
                    .nickname(null)
                    .userImg(null)
                    .email(null)
                    .deleteAt(false)
                    .build();

            userRepository.save(newUser);
        }

        return new ResponseVo(Response.SUCCESS, null);
    }
}
