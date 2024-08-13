package com.example.exerciserepository.Repository;

import com.example.exerciserepository.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);
    @Query("select user from User user where user.email=?1")
    User findUserByEmail(String email);
    @Query("select user from User user where user.role=?1")
    List<User> findUserByRole(String role);
    List<User> findUserByAgeGreaterThanEqual(int age);
    User findUserByUsernameAndPassword(String username, String password);
}
