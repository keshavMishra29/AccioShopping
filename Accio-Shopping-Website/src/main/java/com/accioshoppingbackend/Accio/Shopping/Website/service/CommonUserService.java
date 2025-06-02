package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.exception.UserNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.WrongCredentials;
import com.accioshoppingbackend.Accio.Shopping.Website.model.AppUser;
import com.accioshoppingbackend.Accio.Shopping.Website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonUserService {
    @Autowired
    private UserRepository userRepository;
    public String authenticateUser(String token){
        String [] userCredentials = token.split(":");
        String userEmail = userCredentials[0];
        String userPassword = userCredentials[1];

        AppUser user = userRepository.findByEmail(userEmail);
        if (user == null){
            throw new UserNotFound(String.format("User with email %s does not exist in system", userEmail));
        }
        String originalPassword = user.getPassword();
        if (originalPassword.equals(userPassword)){
            return "Authentication Successfull";
        }
        throw new WrongCredentials(String.format("Password provided by user is incorrect"));
    }
    public AppUser getUserById(UUID userID){
        AppUser user = userRepository.findById(userID).orElse(null);
        return user;
    }
    public Boolean isSeller(UUID sellerID){
        AppUser user = getUserById(sellerID);
        if (user == null){
            return null;
        }
        return user.getUserType().equals("SELLER");
    }
    public void registerUser(AppUser user){
        userRepository.save(user);
    }
}
