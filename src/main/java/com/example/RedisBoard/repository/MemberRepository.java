package com.example.RedisBoard.repository;

import com.example.RedisBoard.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Object> {

}
