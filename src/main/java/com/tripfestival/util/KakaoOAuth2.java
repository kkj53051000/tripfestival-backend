package com.tripfestival.util;

import com.tripfestival.dto.KakaoLoginDto;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoOAuth2 {

    public KakaoLoginDto getUserInfo(String code) {

        String accessToken = getAccessToken(code);

        KakaoLoginDto userInfo = getUserInfoByToken(accessToken);
        return userInfo;
    }

    private String getAccessToken(String authorizedCode) {

        // HttpHeader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "여기에 REST API 키");
        params.add("redirect_uri", "http://localhost:8080/user/kakao/callback");
        params.add("code", authorizedCode);

        // HttpHeader +HttpBody
        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // Http request
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST, kakaoTokenRequest,
                String.class );

        // JSON -> accessToken
        String tokenJson = response.getBody();
        JSONObject rjson = new JSONObject(tokenJson);
        String accessToken = rjson.getString("access_token");

        return accessToken;
    }

    //토큰을 통해 사용자 정보 가져오기

    private KakaoLoginDto getUserInfoByToken(String accessToken) {
        // HttpHeader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader + HttpBody
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        // Http request
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST, kakaoProfileRequest, String.class );

        JSONObject body = new JSONObject(response.getBody());

        System.out.println("body : " + body);

        Long id = body.getLong("id");


        return new KakaoLoginDto(id);
    }

}
