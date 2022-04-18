package com.tripfestival.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@TableGenerator(
        name = "USER_SEQ_GENERATOR",
        table = "TRIPFESTIVAL_SEQUENCES",
        pkColumnValue = "USER_SEQ", allocationSize = 20)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ_GENERATOR")
    @Column(name = "user_id")
    private Long id;

    private String uId;
    private String uPw;
    private String nickname;
    private String userImg;
    private String phoneNumber;
    private String deleteAt;

    public User(String uId, String uPw, String nickname, String userImg, String phoneNumber, String deleteAt) {
        this.uId = uId;
        this.uPw = uPw;
        this.nickname = nickname;
        this.userImg = userImg;
        this.phoneNumber = phoneNumber;
        this.deleteAt = deleteAt;
    }
}
