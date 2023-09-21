package com.example.RedisBoard.controller;

import com.example.RedisBoard.entity.Member;
import com.example.RedisBoard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    // 로그인 페이지
    @GetMapping("/loginPage/")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "errorMessage", required = false) String errorMessage, Model model){
        model.addAttribute("error", error);
        model.addAttribute("errorMessage", errorMessage);
        return "member/login";
    }

    // 로그인
    @PostMapping("/loginPage/login/")
    public void login(String id){
        memberService.loadUserByUsername(id);
    }

    // 로그인 성공 시 이동할 url
    @GetMapping("/loginPage/login/loginSuccess/")
    public String loginSuccess(){
        return "redirect:/";
    }

    // 회원가입 페이지
    @GetMapping("/signUpPage/")
    public String signUpPage(){
        return "member/signUp";
    }

    // 회궝가입
    @PostMapping("/signUpPage/signUp/")
    @ResponseBody
    public String signUp(@RequestBody Member member){
        String res = memberService.signUp(member, passwordEncoder);
        return res;
    }

    // 로그아웃
    @GetMapping("/logout/")
    public void logout(){
        // security 에서 자체적으로 잡아서 사용 x
    }
}
