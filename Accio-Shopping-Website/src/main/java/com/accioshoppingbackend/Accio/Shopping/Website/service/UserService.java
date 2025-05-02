package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.model.ApplicationUser;
import com.accioshoppingbackend.Accio.Shopping.Website.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
        //We need to tell Springboot, hey this class is service class
    // This service layer is to perform all CRUD related operations

    private UserRepository userRepository = new UserRepository();
    public void createUser(ApplicationUser applicationUser){
        //This method wants to save the user into database
        //So this method will call repository layer to save the user
        userRepository.createUser(applicationUser);
    }

    public ApplicationUser getUserByEmail(String email){
        //service layer call repository layer to get user by email
        ApplicationUser user = userRepository.getUserByEmail(email);
        return user;
    }
    public void updateUserByEmail(ApplicationUser applicationUser, String email){
        userRepository.updateUser(email, applicationUser);
    }
    public void deleteUser(String email){
        userRepository.deleteUser(email);
    }
}
