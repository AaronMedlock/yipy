package com.aaronmedlock.yipycore.repository;

import com.aaronmedlock.yipycore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {


    List<User> findByUsername(String username);
}
