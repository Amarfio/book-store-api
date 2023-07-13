package com.work.bookstoreapi.controller;

import com.work.bookstoreapi.user.User;

public class AuthResponse {

    private String email;
    private String accessToken;

    private User user;

    public AuthResponse(String email, String accessToken, User user) {
        this.email = email;
        this.accessToken = accessToken;
        this.setUser(user);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
