package com.assignment.UserManagement.exceptionHandling;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){

    }
    public UserNotFoundException(String msg){
        super(msg);
    }
}
