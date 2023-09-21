package com.example.RedisBoard.controller;

import com.example.RedisBoard.entity.Board;
import com.example.RedisBoard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    BoardService boardService;

    // 메인 페이지
    @GetMapping("/")
    public String main(Model model, @RequestParam(value="page", defaultValue="0") int page){
        Page<Board> boardPage = boardService.findPage(page);
        model.addAttribute("boardList", boardPage);
        return "main";
    }
}
