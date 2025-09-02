package com.example.userformthymeleaf.repository;

import com.example.userformthymeleaf.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();
    private int userId;


    private int nextUserId() {
        return ++userId;
    }

    public List<User> getAllUsers(){
        return users;
    }

    public User addUser(User user){
        user.setUserId(nextUserId());
        users.add(user);
        return user;
    }

    public User getUserById(int userId) {
        for(User u: users){
            if(u.getUserId() == userId) {
                return u;
            }
        }
        return null;
    }

    public boolean updateUser(User newUser) {
        User oldUser = getUserById(newUser.getUserId());
        if(oldUser != null) {
            oldUser.setName(newUser.getName());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setUserRole(newUser.getUserRole());
            return true;
        }
        return false;
    }


}
