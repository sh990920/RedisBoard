package com.example.RedisBoard.service;

import com.example.RedisBoard.entity.Board;
import com.example.RedisBoard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    public void boardAdd(Board board){
        List<Board> boardList = findAll();
        if(boardList.size() > 0){
            int count = boardList.size() - 1;
            board.setIdx(boardList.get(count).getIdx() + 1);
        }else{
            board.setIdx(1L);
        }
        boardRepository.save(board);
    }

    public void boardDelete(Board board){
        Board beforeBoard = findBoard(board);
        if(beforeBoard != null){
            boardRepository.delete(board);
        }
    }

    public void boardUpdate(Board board){
        Board beforeBoard = findBoard(board);
        if(beforeBoard != null){
            boardRepository.save(board);
        }
    }

    public Board findBoard(Board board){
        return boardRepository.findById(board.getIdx()).orElse(null);
    }

    public List<Board> findAll(){
        Iterable<Board> boardIterable = boardRepository.findAll();
        List<Board> boardList = new ArrayList<>();

        boardIterable.forEach(boardList::add);

        return boardList;
    }

    public Page<Board> findPage(int page){
        Pageable pageable = PageRequest.of(page, 5);
        Page<Board> boardList = boardRepository.findAll(pageable);
        return boardList;
    }

    public List<Board> pageBoard(int page, int size){
        int startIndex = (page - 1) * size;
        int endIndex = startIndex + size - 1;
        List<Board> returnBoardList = new ArrayList<>();
        List<Board> boardList = findAll();
        for(int i = startIndex; i < endIndex + 1; i++){
            returnBoardList.add(boardList.get(i));
        }
        return returnBoardList;
    }
}
