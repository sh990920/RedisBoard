package com.example.RedisBoard.controller;

import com.example.RedisBoard.entity.Board;
import com.example.RedisBoard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    // 게시판 글상세보기
    @GetMapping("/post/")
    public String post(Principal principal, Board board, Model model){
        if(principal != null){
            String id = principal.getName();
            model.addAttribute("id", id);
        }else{
            model.addAttribute("id", null);
        }
        Board returnBoard = boardService.findBoard(board);
        model.addAttribute("board", returnBoard);
        return "board/post";
    }

    // 게시판 글 추가 페이지
    @GetMapping("/addPage/")
    public String addPage(Principal principal, Model model){
        String id = principal.getName();
        model.addAttribute("id", id);
        return "board/add";
    }

    // 게시판 글 추가
    @PostMapping("/addPage/add/")
    public String add(Board board){
        boardService.boardAdd(board);
        return "redirect:/";
    }

    // 게시판 글 업데이트 페이지
    @GetMapping("/post/updatePage/")
    public String updatePage(Principal principal, Board board, Model model){
        String id = principal.getName();
        model.addAttribute("id", id);
        Board returnBoard = boardService.findBoard(board);
        model.addAttribute("board", returnBoard);
        return "board/update";
    }

    // 게시판 글 업데이트
    @PostMapping("/post/updatePage/update/")
    public String update(Board board){
        boardService.boardUpdate(board);
        return "redirect:/";
    }

    // 게시판 글 삭제
    @PostMapping("/post/delete/")
    public String delete(Board board){
        boardService.boardDelete(board);
        return "redirect:/";
    }
}
