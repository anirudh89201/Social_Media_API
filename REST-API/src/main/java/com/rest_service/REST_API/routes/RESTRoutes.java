package com.rest_service.REST_API.routes;

import com.rest_service.REST_API.Exceptions.NotFoundException;
import com.rest_service.REST_API.Exceptions.SuccessResponse;
import com.rest_service.REST_API.Posts.PostModel;
import com.rest_service.REST_API.User.UserDAO;
import com.rest_service.REST_API.User.UserModel;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.modelmapper.ModelMapper;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class RESTRoutes {
    @Autowired
    private UserDAO service;
    @GetMapping("/users")
    public List<UserModel> getAllUsers(){
        List<UserModel> users = service.findAll();
        if(!users.isEmpty()){
            return users;
        }else{
            throw new NotFoundException("Data not found..");
        }
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<Object> findSingleUser(@PathVariable Integer id){
            UserModel user = service.findUserID(id);
            if(Objects.equals(user,null))
                    throw new NotFoundException("ANirudh......");

            return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<?> AddUser(@Valid @RequestBody UserModel userModel) {
        System.out.println("Received user: " + userModel);
        boolean isSaved = service.saveUser(userModel);
        if (isSaved)
            return new ResponseEntity<UserModel>(userModel, HttpStatus.CREATED);
        else
            return new ResponseEntity<Object>("There is an Error", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable Integer id) {
        return service.deleteById(id);
    }
    @GetMapping("/users/{id}/posts")
    public ResponseEntity<?> getPosts(@PathVariable Integer id) {
        try {
            UserModel userModel = service.findUserID(id);
            if (userModel != null) {
                return new ResponseEntity<>(userModel.getPosts(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found with the given ID", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<?> PutUser(@PathVariable Integer id,@Valid @RequestBody UserModel userModel) {
        UserModel existingUser = service.findUserID(id);
        if(existingUser == null)
            return new ResponseEntity<>("User with the ID does not Found..",HttpStatus.NOT_FOUND);
        else{
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.map(userModel,existingUser);
            service.saveUser(existingUser);
            return new ResponseEntity<>("resource updated sucessfully",HttpStatus.OK);
        }
    }
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<?> createPosts(@PathVariable Integer id, @RequestBody PostModel postModel) {
        try {
            UserModel userModel = service.findUserID(id);
            if (userModel != null) {
                postModel.setUser(userModel);
                return service.createPosts(postModel);
            } else {
                return new ResponseEntity<>("User Not Found with the given ID", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}