package com.demo.web.back.sys.repository;

import org.springframework.stereotype.Repository;

import com.demo.repository.BaseRepository;
import com.demo.web.back.sys.entity.User;

 

@Repository
public interface UserRepository extends BaseRepository<User,Long> {
     
    public User findByUsername(String username);

   
}
