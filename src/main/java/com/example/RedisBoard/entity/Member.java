package com.example.RedisBoard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "Member")
public class Member {
    @Id
    private String id; // 유저 id

    private String password; // 유저 pw

    private String nickname; // 유저 닉네임

    private String rollName; // 유저 권한
}
