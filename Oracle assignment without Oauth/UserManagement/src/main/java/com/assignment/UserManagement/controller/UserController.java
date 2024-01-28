package com.assignment.UserManagement.controller;

import com.assignment.UserManagement.exceptionHandling.UserNotFoundException;
import com.assignment.UserManagement.model.AuthUser;
import com.assignment.UserManagement.model.User;
import com.assignment.UserManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private int flag=0;
    @Autowired
    private UserService userService;
   /* @Autowired
    private AuthService authService;*/



    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        System.out.println("post controller");
        System.out.println(user);
       return ResponseEntity.ok(userService.addUser(user));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthUser authUser) {
        if(userService.authenticate(authUser)){
            flag=1;
        }
        else {
            throw new UserNotFoundException("Authentication failed, username or password is invalid");
        }
        return ResponseEntity.ok("Sign in Successfull");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editUser(@PathVariable int id, @RequestBody User user) {
        if (flag!=1)
            throw new UserNotFoundException("Authentication failed, username or password is invalid");
        user.setId(id);
        return ResponseEntity.ok(userService.editUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        if (flag!=1)
            throw new UserNotFoundException("Authentication failed, username or password is invalid");
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted the user details with"+id+"successfully");
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id) {
        if (flag!=1)
            throw new UserNotFoundException("Authentication failed, username or password is invalid");
        return userService.findUserById(id);
    }

    @GetMapping
    public Optional<List<User>> findAllUsers() {
        if (flag!=1)
            throw new UserNotFoundException("Authentication failed, username or password is invalid");
        return userService.findAllUsers();
    }
}