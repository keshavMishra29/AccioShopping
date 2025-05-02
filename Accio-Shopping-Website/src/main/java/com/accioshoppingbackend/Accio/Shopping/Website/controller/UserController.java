package com.accioshoppingbackend.Accio.Shopping.Website.controller;

import com.accioshoppingbackend.Accio.Shopping.Website.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserService userService = new UserService();
    @PostMapping("/api/register/user")
    public String registerUser(@RequestBody ApplicationUser applicationUser){
        //Logic for saving user information should be written in service layer
        userService.createUser(applicationUser);
        return "User got saved successfully";
    }
    //We want to get all user details by email
    @GetMapping("/api/user")
    public ApplicationUser getUserByEmail(@RequestParam String email){
        //Controller layer will call service layer to get user by email
        ApplicationUser user = userService.getUserByEmail(email);
        return user;
    }
    //Update user detail by method
    @PutMapping("/api/user/update")
    public ApplicationUser updateUserByEmail(@RequestBody ApplicationUser applicationUser,
                                             @RequestParam String email){
        userService.updateUserByEmail(applicationUser, email);
        return userService.getUserByEmail(email);
    }
}
