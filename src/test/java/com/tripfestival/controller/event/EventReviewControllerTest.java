package com.tripfestival.controller.event;

import com.tripfestival.controller.BaseControllerTest;
import com.tripfestival.domain.event.EventReview;
import com.tripfestival.domain.user.Role;
import com.tripfestival.domain.user.User;
import com.tripfestival.repository.event.EventReviewRepository;
import com.tripfestival.repository.user.UserRepository;
import com.tripfestival.request.event.EventReviewProcessRequest;
import com.tripfestival.util.JwtUtil;
import com.tripfestival.vo.Response;
import com.tripfestival.vo.ResponseVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventReviewControllerTest extends BaseControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventReviewRepository eventReviewRepository;

    @Autowired
    JwtUtil jwtUtil;

    User user;
    EventReview eventReview;
    String jwtKey;

    @BeforeEach
    void setup() {
        user = User.builder()
                .uid("test")
                .role(Role.USER)
                .deleteAt(false)
                .build();
        userRepository.save(user);

        eventReview = EventReview.builder()
                .content("test")
                .score((byte) 1)
                .user(user)
                .build();
        eventReviewRepository.save(eventReview);

        jwtKey = jwtUtil.createToken(user.getId(), Role.USER);
    }

    @Test
    void EVENT_REVIEW_PROCESS_TEST() throws Exception {
        //given
        EventReviewProcessRequest req = EventReviewProcessRequest.builder()
                .content("test")
                .score((byte) 10)
                .build();

        String jsonReq = objectMapper.writeValueAsString(req);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/user/eventReviewProcess")
                .header(HttpHeaders.AUTHORIZATION, jwtKey)
                .contentType(jsonMediaType)
                .content(jsonReq));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }

    @Test
    void EVENT_REVIEW_REMOVE_TEST() throws Exception {
        //given

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/user/eventReviewRemove/" + eventReview.getId())
                .header(HttpHeaders.AUTHORIZATION, jwtKey));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new ResponseVo(Response.SUCCESS, null))))
                .andDo(print());
    }
}