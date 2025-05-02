package com.accioshoppingbackend.Accio.Shopping.Website.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Tell controller class, This is spring project
public class Controller {
    //We need to define some kind of URL such that client is going to communicate with the server
    @GetMapping("/hello")
    public String sayHi(){
        return "Hello, This is your Captain";
    }
    @PostMapping("/api/register")
    //Request body:- Data send by client to save information in the srver
    public void createAccount(@RequestBody ApplicationUser applicationUser){
        System.out.println(applicationUser.getFirstName());
        System.out.println(applicationUser.getPassword());
        System.out.println(applicationUser.getEmail());
    }
}
