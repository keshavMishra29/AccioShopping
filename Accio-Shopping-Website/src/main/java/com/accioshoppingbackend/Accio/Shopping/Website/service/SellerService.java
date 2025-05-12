package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.exception.AccessNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.UserNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.model.AppUser;
import com.accioshoppingbackend.Accio.Shopping.Website.model.Product;
import com.accioshoppingbackend.Accio.Shopping.Website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SellerService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private CommonUserService userService;
    public void addProduct(Product product, UUID sellerID){
        AppUser user = userService.getUserById(sellerID);
        if (user == null){
            throw new UserNotFound(String.format("User not found with ID %s", sellerID.toString()));
        }
        if (user.getUserType().equals("BUYER")){
            throw new AccessNotFound(String.format("User with id %s does not have access to add product", sellerID.toString()));
        }
        product.setSeller(user);
        productRepository.save(product);
    }
}
