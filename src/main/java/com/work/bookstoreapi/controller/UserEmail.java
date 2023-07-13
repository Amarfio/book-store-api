package com.work.bookstoreapi.controller;

public class UserEmail {
    private String email;

    public UserEmail(String email){
        this.setEmail(email);
    }

    //get user email
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
