package com.assignment.UserManagement.service;

import com.assignment.UserManagement.exceptionHandling.UserNotFoundException;
import com.assignment.UserManagement.model.User;
import com.assignment.UserManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;




    public String addUser(User user) {
        System.out.println("Post Service");
        validateUser(user);
        finByUsername(user.getName());
        userRepository.save(user);
        return "User added successfully";
    }

    public String editUser(User user) {
        validateUser(user);
        findUserById(user.getId());
        userRepository.save(user);
        return "Updated records successfully";
    }

    public void deleteUser(int id) {
        findUserById(id);
        userRepository.deleteById(id);
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User with Id does not exist"));
    }

    public Optional<List<User>> findAllUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(users);
        }
    }
    public void finByUsername(String username){
        Optional<User> user = userRepository.findByName(username);
        if(!user.isEmpty())
            throw new RuntimeException("Username alredy existed");
    }
    private void validateUser(User user) {
        // Validate password format
        if (!isValidPassword(user.getPassword())) {
            throw new IllegalArgumentException("Password must have at least 8 characters, one special character, one uppercase, and one lowercase character.");
        }
    }

    private boolean isValidPassword(String password) {
        // Password validation criteria: At least 8 characters, one special character, one uppercase, and one lowercase character
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return Pattern.matches(passwordPattern, password);
    }

}
