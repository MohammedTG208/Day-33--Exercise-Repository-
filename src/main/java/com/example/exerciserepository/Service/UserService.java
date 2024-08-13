package com.example.exerciserepository.Service;

import com.example.exerciserepository.Api.ApiException;
import com.example.exerciserepository.Model.User;
import com.example.exerciserepository.Repository.UserRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUser(){
        if (userRepository.findAll().isEmpty()){
            throw new ApiException("No users found");
        }
        return userRepository.findAll();
    }

    public void addNewUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        if (userRepository.findUserById(id)!=null){
            userRepository.deleteById(id);
        }
        throw new ApiException("No user found");
    }

    public void updateUser(User user,Integer id){
        if (userRepository.findUserById(id)!=null){
            User oldUser = userRepository.findUserById(id);
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setRole(user.getRole());
            oldUser.setAge(user.getAge());
            oldUser.setUsername(user.getUsername());
            userRepository.save(oldUser);
        }
        throw new ApiException("No user found");
    }

    public User getUserById(Integer id){
        if (userRepository.findUserById(id)!=null){
            return userRepository.findUserById(id);
        }
        throw new ApiException("No user found");
    }
    public List<User> getUserByRoles(String role){
        if (userRepository.findUserByRole(role)!=null){
            return userRepository.findUserByRole(role);
        }
        throw new ApiException("No user found by this role");
    }

    public List<User> getAllUserByAge(int age){
        if (userRepository.findUserByAgeGreaterThanEqual(age).isEmpty()){
            throw new ApiException("No user found by this age");
        }
        return userRepository.findUserByAgeGreaterThanEqual(age);
    }

    public User getUserByEmailAndPassword(String username, String password){
        if (userRepository.findUserByUsernameAndPassword(username, password)!=null){
            return userRepository.findUserByUsernameAndPassword(username, password);
        }
        throw new ApiException("No user found by this email and password");
    }

    public User getByEmail(String email){
        if (userRepository.findUserByEmail(email)!=null){
            return userRepository.findUserByEmail(email);
        }
        throw new ApiException("No user found by this email");
    }
}
