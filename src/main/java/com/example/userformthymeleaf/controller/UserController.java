package com.example.userformthymeleaf.controller;
import com.example.userformthymeleaf.model.User;
import com.example.userformthymeleaf.model.UserRole;
import com.example.userformthymeleaf.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers(); //get users from service
        model.addAttribute("users", users); //add users to model with key "users"
        return "showusers"; //ref html
    }

    //Display the user registration form
    @GetMapping("/register")
    public String showUserRegistrationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("userRoles", UserRole.values());
        return "user-registration-form";
    }

    // POST - Handle the form submission
    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        userService.addUser(user);
        return "redirect:/user/users";
    }

    //
    @GetMapping("/edit/{userId}")
    public String editUser(@PathVariable int userId, Model model) {
        User user = userService.getUserById(userId);
        if(user == null) {
            throw new IllegalArgumentException("Invalid user id");
        }
        model.addAttribute("user", user);
        model.addAttribute("userRoles", UserRole.values());
        return "user-edit-form";
    }

    //Handle the form submission
    @PostMapping("/update")
    public String editUser(@ModelAttribute User user) {
        if(userService.updateUser(user)){
            return "redirect:/user/users";
        }

        throw new IllegalArgumentException("User not found");
    }
}
