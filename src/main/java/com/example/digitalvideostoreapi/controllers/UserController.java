package com.example.digitalvideostoreapi.controllers;

import com.example.digitalvideostoreapi.CustomizedResponse;
import com.example.digitalvideostoreapi.models.UserModel;
import com.example.digitalvideostoreapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "https://github.com/yf-chang/Digital-Video-Store.git")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //get all users
    @GetMapping("/users")
    public ResponseEntity getUsers()
    {
        return new ResponseEntity(new CustomizedResponse( " A list of all users ", userService.getUsers()), HttpStatus.OK);
    }



    @GetMapping("/users/{id}")
    public ResponseEntity getAUsers(@PathVariable("id") String id)
    {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse(" User with id " + id , Collections.singletonList(userService.getAUser(id)));
        }
        catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage());
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }



    @PostMapping(value = "/users", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createUsers(@RequestBody UserModel user)
    {
        CustomizedResponse customizedResponse = null;
        try{
            customizedResponse = new CustomizedResponse( " User created successfully", Collections.singletonList(userService.addUser(user)));
        }
        catch(Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage());
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
}
