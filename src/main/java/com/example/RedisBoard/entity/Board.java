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
@RedisHash(value = "Board")
public class Board {
    @Id
    private Long idx; // 글 번호

    private String memberId; // 작성한 유저 id

    private String title; // 글 제목

    private String contents; // 글 내용
}
