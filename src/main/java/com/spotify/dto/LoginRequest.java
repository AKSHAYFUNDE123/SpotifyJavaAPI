package com.spotify.dto;

public class LoginRequest {

    private String username;
    private String password;

    // 👉 Default Constructor
    public LoginRequest() {
    }

    // 👉 Parameterized Constructor (optional, if needed)
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 👉 Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // 👉 Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 👉 toString() (optional)
    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
