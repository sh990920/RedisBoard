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

    @GetMapping("/loginPage/")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "errorMessage", required = false) String errorMessage, Model model){
        model.addAttribute("error", error);
        model.addAttribute("errorMessage", errorMessage);
        return "member/login";
    }

    @PostMapping("/loginPage/login/")
    public void login(String id){
        memberService.loadUserByUsername(id);
    }

    @GetMapping("/loginPage/login/loginSuccess/")
    public String loginSuccess(){
        return "redirect:/";
    }

    @GetMapping("/signUpPage/")
    public String signUpPage(){
        return "member/signUp";
    }

    @PostMapping("/signUpPage/signUp/")
    @ResponseBody
    public String signUp(@RequestBody Member member){
        String res = memberService.signUp(member, passwordEncoder);
        return res;
    }

    @GetMapping("/logout/")
    public void logout(){

    }
}
