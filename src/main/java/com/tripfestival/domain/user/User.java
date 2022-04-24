package com.tripfestival.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
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

    private String uid;
    private String upw;
    private String nickname;
    private String userImg;
    private String email;
    private boolean deleteAt;

    public User(String uid, String upw, String nickname, String userImg, String email, boolean deleteAt) {
        this.uid = uid;
        this.upw = upw;
        this.nickname = nickname;
        this.userImg = userImg;
        this.email = email;
        this.deleteAt = deleteAt;
    }
}
