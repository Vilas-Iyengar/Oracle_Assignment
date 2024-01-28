package com.assignment.UserManagement.controller;

import com.assignment.UserManagement.model.User;
import com.assignment.UserManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
   /* @Autowired
    private AuthService authService;*/



    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        System.out.println("post controller");
       return ResponseEntity.ok(userService.addUser(user));
    }
    /*@PostMapping("/authenticate")
    public String authenticateUser(@RequestParam String username, @RequestParam String password) {
        // Authenticate user
        if (authService.authenticateUser(username, password)) {
            return "Authentication successful";
        } else {
            return "Authentication failed";
        }
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<String> editUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        return ResponseEntity.ok(userService.editUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted the user details with"+id+"successfully");
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @GetMapping
    public Optional<List<User>> findAllUsers() {
        return userService.findAllUsers();
    }
}