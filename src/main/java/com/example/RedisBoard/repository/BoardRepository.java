package com.example.RedisBoard.repository;

import com.example.RedisBoard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<Board, Object> {
    Page<Board> findAll(Pageable pageable);
}
