package com.example.userformthymeleaf.service;

import com.example.userformthymeleaf.model.User;
import com.example.userformthymeleaf.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public User addUser(User user){
        return userRepository.addUser(user);
    }

    public User getUserById(int userId){
        return userRepository.getUserById(userId);
    }

    public boolean updateUser(User user){
        return userRepository.updateUser(user);
    }


}
