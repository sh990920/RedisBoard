package com.example.RedisBoard.service;

import com.example.RedisBoard.entity.Member;
import com.example.RedisBoard.repository.MemberRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    // 로그인
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username).orElse(null);
        if(member == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(member.getId())
                .password(member.getPassword())
                .roles(member.getRollName())
                .build();
    }
    //회원가입
    public void signUp(Member member, PasswordEncoder passwordEncoder){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRollName("USER");
        memberRepository.save(member);
    }
}
