package com.accioshoppingbackend.Accio.Shopping.Website.service;

import com.accioshoppingbackend.Accio.Shopping.Website.exception.AccessNotFound;
import com.accioshoppingbackend.Accio.Shopping.Website.exception.InvalidProductID;
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
    @Autowired
    private ProductService productService;
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
    public String removeProduct(UUID sellerID, UUID productID){
        Boolean isSeller = userService.isSeller(sellerID);
        if (isSeller == null){
            throw new UserNotFound(String.format("User with id %s does not exist", sellerID.toString()));
        }
        if (isSeller == false){
            throw new AccessNotFound(String.format(
                    "User with id %s does not have access to delete product",
                    sellerID.toString()));
        }
        boolean validProduct = productService.ValidateProductID(productID);
        if (validProduct == false){
            throw new InvalidProductID(String.format(
                    "Product with name %s does not exist in system",
                    productID.toString()));
        }
        Product product = productService.getProductByID(productID);
        System.out.println(product);
        AppUser owner = product.getSeller();

        if (!owner.getId().equals(sellerID)){
            throw new AccessNotFound(String.format(
                    "User with name %s does not have access to remove product %s",
                    owner.getName(),
                    product.getProductName()
            ));
        }

        productService.removeProduct(product);
        return String.format(
                "Seller with name %s removed product %s",
                owner.getName(),
                product.getProductName());
    }
}
